package com.bihe0832.plugin.theme.host;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * Created by hardyshi on 17/3/15.
 */
public class ThemeManage {

    private static final String LOG_TAG = "bihe0832 theme";

    private Resources mThemeResources;
    private Context mContext;

    private static volatile ThemeManage instance = null;
    public static ThemeManage getInstance() {
        if (instance == null) {
            synchronized (ThemeManage.class) {
                if (instance == null) {
                    instance = new ThemeManage();
                }
            }
        }
        return instance;
    }

    private ThemeManage() {

    }

    public void onCreate(Activity activity) {
        mContext = activity.getApplicationContext();
    }

    public Resources getThemeResources() {
        return mThemeResources == null ? mContext.getResources() : mThemeResources;
    }

    public void installTheme(String themName){
        if(null == mContext){
            Log.e(LOG_TAG,"ThemeManage context is null");
            return;
        }
        //插件保存的位置
        String libPath = mContext.getFilesDir().getAbsolutePath()+ File.separator + themName + ".apk";
        prepareTheme(themName);

        if(new File(libPath).exists()){
            loadResources(libPath);
        }else{
            Log.e(LOG_TAG, themName + " file not exist!");
        }
    }

    private void prepareTheme(String themName){
        String libPath = mContext.getFilesDir().getAbsolutePath()+ File.separator + themName + ".apk";
        try{
            InputStream is = mContext.getAssets().open(themName + ".apk");
            FileOutputStream fos = new FileOutputStream(new File(libPath));
            byte[] buffer = new byte[1024];
            int byteCount=0;
            while((byteCount=is.read(buffer))!=-1) {//循环从输入流读取 buffer字节
                fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
            }
            fos.flush();//刷新缓冲区
            is.close();
            fos.close();

        }catch (Exception e){
            Log.d(LOG_TAG,Log.getStackTraceString(e));
        }
    }

    private void loadResources(String dexPath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, dexPath);
            Resources superRes = mContext.getResources();
            mThemeResources = new Resources(assetManager, superRes.getDisplayMetrics(),superRes.getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
