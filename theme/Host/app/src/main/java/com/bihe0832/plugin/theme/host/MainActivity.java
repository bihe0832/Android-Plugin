package com.bihe0832.plugin.theme.host;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;



public class MainActivity extends Activity {

    private static final String LOG_TAG = "bihe0832";

    private TextView hostTextView;
    private ImageView hostImgView;
    private TextView testTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ThemeManage.getInstance().onCreate(this);
        hostTextView = (TextView)findViewById(R.id.hostTextView);
        hostImgView = (ImageView)findViewById(R.id.hostImgView);
        testTextView = (TextView)findViewById(R.id.resultTextView);


        Button changeToTheme1Btn = (Button)findViewById(R.id.changeToTheme1);
        changeToTheme1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"加载主题1",Toast.LENGTH_LONG).show();
                ThemeManage.getInstance().installTheme("Theme1");
                updateContent();
            }
        });

        Button changeToTheme2Btn = (Button)findViewById(R.id.changeToTheme2);
        changeToTheme2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"加载主题2",Toast.LENGTH_LONG).show();
                ThemeManage.getInstance().installTheme("Theme2");
                updateContent();
            }
        });
    }

    private void updateContent(){
        try{
            hostTextView.setText(ThemeManage.getInstance().getThemeResources().getString(R.string.hostDesc));
            hostTextView.setTextColor(ThemeManage.getInstance().getThemeResources().getColor(R.color.hostDesc));
            hostTextView.setTextSize(ThemeManage.getInstance().getThemeResources().getDimension(R.dimen.hostDesc));
            hostImgView.setImageDrawable(ThemeManage.getInstance().getThemeResources().getDrawable(R.drawable.ic_launcher));
            testTextView.setText(getResources().getString(R.string.test_result));
        }catch(Exception e){
            Log.i("bihe0832", "error:"+Log.getStackTraceString(e));
        }
    }
}
