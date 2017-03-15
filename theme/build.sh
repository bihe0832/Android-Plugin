#!/bin/sh
#函数定义，检测执行结果
function checkResult() {  
   result=$?
   echo "result : $result"
   if [ $result -eq 0 ];then
      echo "checkResult: execCommand succ"
   else
    echo "checkResult: execCommand failed"
    exit $result
   fi
}  


echo "********build：set env *******"

export ANDROID_HOME=$ANDROID_SDK
export JAVA_HOME=`/usr/libexec/java_home -v 1.7`
export ANDROID_NDK_HOME=$ANDROIDNDK_LINUX_R10C
export PATH=$JAVA_HOME/bin:$GRADLE_HOME/bin:$PATH
echo $ANDROID_HOME
echo $JAVA_HOME
echo $ANDROID_NDK_HOME

echo "********build mkdir bin *******"

localPath=`pwd`
echo $localPath
if [ ! -d "./bin" ]; then
  mkdir $localPath/bin
fi
cd $localPath/bin && rm -rf  * && cd $localPath

echo "********build：build Theme1 *******"
chmod +x $localPath/Theme1/gradlew
cd $localPath/Theme1 && ./gradlew clean build
checkResult

#拷贝最新资源
echo "********build：copy Theme1 apk to bin *******"
cp -r $localPath/Theme1/app/build/outputs/apk/app-all-debug.apk $localPath/bin/Theme1.apk

echo "********build：copy Theme1 apk to host *******"
cp -r $localPath/Theme1/app/build/outputs/apk/app-all-debug.apk $localPath/Host/app/src/main/assets/Theme1.apk

echo "********build：build Theme2 *******"
chmod +x $localPath/Theme2/gradlew
cd $localPath/Theme2 && ./gradlew clean build
checkResult

#拷贝最新资源
echo "********build：copy Theme2 apk to bin *******"
cp -r $localPath/Theme2/app/build/outputs/apk/app-all-debug.apk $localPath/bin/Theme2.apk

echo "********build：copy Theme2 apk to host *******"
cp -r $localPath/Theme2/app/build/outputs/apk/app-all-debug.apk $localPath/Host/app/src/main/assets/Theme2.apk


echo "********build：clean host *******"
chmod +x $localPath/Host/gradlew
cd $localPath/Host && ./gradlew clean build
checkResult

echo "********build：copy host apk to bin *******"
cp -r $localPath/Host/app/build/outputs/apk/app-all-debug.apk $localPath/bin/theme-host.apk
cp -r $localPath/Host/app/build/outputs/apk/app-all-debug.apk $localPath/../res/theme-host.apk
checkResult