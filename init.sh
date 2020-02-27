echo ">>>>>> clean package"
mvn clean package -Dmaven.test.skip=true

echo ">>>>>> copy jar"
mkdir -p target

sh startEureka.sh
sh startConfigCenter.sh

echo ">>>>>> docker build"

serverName="license,access"
OLD_IFS=$IFS
IFS=","
servers=($serverName)
IFS="$OLD_IFS"

for app in ${servers[@]}
do
echo ">>>>>> compile ${app}"
# mvn clean package -pl microserver/${app} -Dmaven.test.skip=true
cp microserver/access/target/${app}-*.jar target/${app}-server.jar

echo ">>>>>> docker build ${app}-server"
docker build -t ${app}-server:0.0.1 --build-arg APP=${app}-server .
docker stop ${app}-server
docker rm ${app}-server
winpty docker run -d -it -P --add-host localhost:192.168.1.102 --name ${app}-server ${app}-server:0.0.1
done

