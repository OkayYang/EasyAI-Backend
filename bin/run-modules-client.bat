@echo off
echo.
echo [��Ϣ] ʹ��Jar��������Modules-Client���̡�
echo.

cd %~dp0
cd ../easyai-modules/easyai-client/target

set JAVA_OPTS=-Xms512m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m

java -Dfile.encoding=utf-8 %JAVA_OPTS% -jar easyai-modules-client.jar

cd bin
pause