pipeline {
    agent any

    triggers {
        pollSCM('* * * * *')
    }

    environment {
        REMOTE_HOST = "appserver@192.168.11.115"
        PROJECT_DIR = "/home/appserver/ecom-ii-bdcc-app"
    }

    stages {
        stage('Deploy on Remote VM') {
            steps {
                echo "Deploying to ${REMOTE_HOST}"
                sshagent(credentials: ['my-vm-ssh-key']) {
                    sh """
                        ssh -o StrictHostKeyChecking=no ${REMOTE_HOST} '
                            cd ${PROJECT_DIR} &&
                            git pull origin master &&
                            docker compose up -d
                        '
                    """
                }
            }
        }
    }

    post {
        always {
            echo "This is Jenkinsfile"
        }
    }
}
