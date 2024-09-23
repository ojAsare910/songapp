pipeline {
 agent any

  tools {
     jdk 'JDK 21'
     maven 'Maven 3.9.9'
 }

 environment {
     SERVER_PORT = '8083'
     POSTGRES_URL = 'jdbc:postgresql://192.168.56.38:5432/test?currentSchema=musicdb'
     POSTGRES_USER = 'postgres'
     POSTGRES_PASSWORD = 'password123'
     REDIS_HOST = '192.168.56.38'
     REDIS_PORT = '6379'
     DOCKER_IMAGE = 'ojasare910/songapp:latest'
 }

 stages {
//      stage('Fetch Code') {
//          steps {
//              git branch: 'main', url: 'https://github.com/ojAsare910/songapp.git'
//          }
//      }

     stage('Build') {
         steps {
             sh 'mvn clean package'
         }
     }

     stage('Test') {
         steps {
             sh 'mvn test'
         }
     }

     stage('Build Docker Image') {
                 steps {
                     script {
                        docker.build("${DOCKER_IMAGE}")
                     }
                 }
             }

             stage ('Push Docker Image') {
                 steps {
                     withCredentials([string(credentialsId: 'docker-hub', variable: 'dockerhubpwd')]) {
                         sh 'docker login -u ojasare910 -p ${dockerhubpwd}'
                     }
                     sh 'docker push ${DOCKER_IMAGE}'
                 }
             }

     stage('Deploy') {
         steps {
             sh '''
                   docker run -d \
                   -p ${SERVER_PORT}:${SERVER_PORT} \
                   --name songapp-api \
                   -e SPRING_DATASOURCE_URL=${POSTGRES_URL} \
                   -e SPRING_DATASOURCE_USERNAME=${POSTGRES_USER} \
                   -e SPRING_DATASOURCE_PASSWORD=${POSTGRES_PASSWORD} \
                   -e SPRING_DATA_REDIS_HOST=${REDIS_HOST} \
                   -e SPRING_DATA_REDIS_PORT=${REDIS_PORT} \
                   -e SERVER_PORT=${SERVER_PORT} \
                   -v /path/on/host/logs:/app/logs \
                   ${DOCKER_IMAGE}
               '''
         }
     }
 }
}