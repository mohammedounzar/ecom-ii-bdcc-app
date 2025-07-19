pipeline {
    agent any
    environment {
        REMOTE_HOST = "appserver@192.168.11.105"
        PROJECT_DIR = "/home/appuser/ecom-ii-bdcc-app"
    }
    stages {
        stage('Deploy on Remote VM') {
            steps {
                sh """
                ssh -o StrictHostKeyChecking=no $REMOTE_HOST '
                    cd $PROJECT_DIR &&
                    git pull origin main &&
                    docker compose up -d
                '
                """
            }
        }
    }
    post {
        always {
            echo "This is Jenkinsfile"
        }
    }
}