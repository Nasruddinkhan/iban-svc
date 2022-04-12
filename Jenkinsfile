// Declarative //
pipeline {
    agent any
    environment {
        EMAIL_RECIPIENTS = 'nasruddinkhan44@gmaiil.com'
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Test') {
            steps {
               bat(/"${mvnHome}\bin\mvn" -Dintegration-tests.skip=true clean package/)
                                       def pom = readMavenPom file: 'pom.xml'
                                       print pom.version
                                       junit '**//*target/surefire-reports/TEST-*.xml'
                                       archive 'target*//*.jar'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}