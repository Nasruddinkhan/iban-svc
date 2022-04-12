// Declarative //
pipeline {
    agent any
    environment {
        EMAIL_RECIPIENTS = 'nasruddinkhan44@gmaiil.com'
    }
    stages {
        stage('Clean') {
            steps {
                echo 'Clean..'
                bat "mvn clean"

            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                bat "mvn test"
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                 bat "mvn package"
            }
        }
    }
}