#! /bin/bash

cd driver
sudo mvn spring-boot:build-image -DskipTests
sudo docker image tag driver:0.0.1-SNAPSHOT erickluz360/driver:latest 
sudo docker image push erickluz360/driver:latest
cd ..

cd passenger
sudo mvn spring-boot:build-image -DskipTests
sudo docker image tag passenger:0.0.1-SNAPSHOT erickluz360/passenger:latest 
sudo docker image push erickluz360/passenger:latest
cd ..

cd trip
sudo mvn spring-boot:build-image -DskipTests
sudo docker image tag trip:0.0.1-SNAPSHOT erickluz360/trip:latest 
sudo docker image push erickluz360/trip:latest
cd ..
