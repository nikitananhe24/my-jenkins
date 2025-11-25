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
                   sh '/opt/maven/bin/mvn  sonar:sonar \\-Dsonar.projectKey=project \\-Dsonar.host.url=http://3.141.202.90:9000 \\-Dsonar.login=967deab55bf3de34de63964aa4d663dab1341750'

            }
        }
        stage('deploy') {
            steps {
                echo "deploy sucess"
            }
        }
    }
}