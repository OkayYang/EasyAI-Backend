#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
echo "begin copy sql "
cp ../sql/ry_20240629.sql ./mysql/db
cp ../sql/ry_config_20240914.sql ./mysql/db

# copy html
echo "begin copy html "
cp -r ../easyai-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy easyai-gateway "
cp ../easyai-gateway/target/easyai-gateway.jar ./ruoyi/gateway/jar

echo "begin copy easyai-auth "
cp ../easyai-auth/target/easyai-auth.jar ./ruoyi/auth/jar

echo "begin copy easyai-visual "
cp ../easyai-visual/easyai-monitor/target/easyai-visual-monitor.jar  ./ruoyi/visual/monitor/jar

echo "begin copy easyai-modules-system "
cp ../easyai-modules/easyai-system/target/easyai-modules-system.jar ./ruoyi/modules/system/jar

echo "begin copy easyai-modules-file "
cp ../easyai-modules/easyai-file/target/easyai-modules-file.jar ./ruoyi/modules/file/jar

echo "begin copy easyai-modules-job "
cp ../easyai-modules/easyai-job/target/easyai-modules-job.jar ./ruoyi/modules/job/jar

echo "begin copy easyai-modules-gen "
cp ../easyai-modules/easyai-gen/target/easyai-modules-gen.jar ./ruoyi/modules/gen/jar

