pipeline {
    agent {label 'slave'}
    stages {
        stage('pull-stage') {
            steps {
                echo "git branch: 'main', url: 'https://github.com/Anilbamnote/student-ui-app.git'"
            }
        }
        stage('Build') {
            steps {
                sh '/opt/maven/bin/mvn clean package'
            }
        }
        stage('test') {
            steps {
                    echo "test success"
            }
        }
        stage('deploy') {
            steps {
                echo "deploy sucess"
            }
        }
    }
}