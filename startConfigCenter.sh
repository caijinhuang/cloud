echo ">>>>>> clean package"
mvn clean package -pl config-server -Dmaven.test.skip=true

echo ">>>>>> build config-server"
cd config-server
docker build -t config-server:0.0.1 -f Dockerfile --build-arg APP=config-server .
docker stop config-server
docker rm config-server
winpty docker run -d -it -P -e PROFILE=dev --add-host localhost:192.168.1.102 --name config-server config-server:0.0.1
cd ..