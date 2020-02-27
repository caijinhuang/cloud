echo ">>>>>> clean package"
mvn clean package -pl eureka -Dspring.profiles.active=master -Dmaven.test.skip=true

echo ">>>>>> build eureka"
cd eureka
docker build -t eureka-server:0.0.1 -f Dockerfile --build-arg APP=eureka-server .
docker stop eureka-server
docker rm eureka-server
winpty docker run -d -it -p 8761:8888 -e PROFILE=master --add-host localhost:192.168.1.102 --name eureka-server eureka-server:0.0.1
cd ..