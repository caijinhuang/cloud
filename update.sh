#!/bin/bash
serverName="license,access"
#获取参数
while [ -n "$1" ]
do
        case "$1" in
                -s)
                    serverName=$2
                    shift 2;;
                *)
                    echo "end" ;;
        esac
done

OLD_IFS=$IFS
IFS=","
servers=($serverName)
IFS="$OLD_IFS"

echo ">>>>>> mvn build"
# mvn clean package -pl microserver -Dmaven.test.skip=true
mvn clean package -Dmaven.test.skip=true
mkdir -p target
for app in ${servers[@]}
do
echo ">>>>>> copy ${app}"
cp microserver/${app}/target/${app}-*.jar target/${app}-server.jar

echo ">>>>>> docker build ${app}-server"
docker build -t ${app}-server:0.0.1 --build-arg APP=${app}-server .
docker stop ${app}-server
docker rm ${app}-server
winpty docker run -d -it -P --add-host localhost:10.1.11.138 --name ${app}-server ${app}-server:0.0.1

done

