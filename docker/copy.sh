#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
echo "begin copy sql "
cp ../sql/ai-easyai-admin.sql ./mysql/db
cp ../sql/ai-easyai-client.sql ./mysql/db
cp ../sql/ai-easyai-config.sql ./mysql/db

# copy html
echo "begin copy html "
mkdir -p ./nginx/html/dist  # 确保目标目录存在
cp -r ../easyai-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy easyai-gateway "
cp ../easyai-gateway/target/easyai-gateway.jar ./easyai/gateway/jar

echo "begin copy easyai-auth "
cp ../easyai-auth/target/easyai-auth.jar ./easyai/auth/jar

echo "begin copy easyai-visual "
cp ../easyai-visual/easyai-monitor/target/easyai-visual-monitor.jar  ./easyai/visual/monitor/jar

echo "begin copy easyai-modules-system "
cp ../easyai-modules/easyai-system/target/easyai-modules-system.jar ./easyai/modules/system/jar

echo "begin copy easyai-modules-file "
cp ../easyai-modules/easyai-file/target/easyai-modules-file.jar ./easyai/modules/file/jar

echo "begin copy easyai-modules-job "
cp ../easyai-modules/easyai-job/target/easyai-modules-job.jar ./easyai/modules/job/jar

echo "begin copy easyai-modules-gen "
cp ../easyai-modules/easyai-gen/target/easyai-modules-gen.jar ./easyai/modules/gen/jar

echo "begin copy easyai-modules-client "
cp ../easyai-modules/easyai-client/target/easyai-modules-client.jar ./easyai/modules/client/jar
