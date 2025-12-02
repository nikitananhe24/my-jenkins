pipeline {
    agent { label 'slave' }

    stages {

        stage('pull-stage') {
            steps {
                git branch: 'main', url: 'https://github.com/Athu1717/my-student-app.git'
            }
        }

        stage('Build') {
            steps {
                sh '/opt/maven/bin/mvn clean package'
            }
        }

        stage('test') {
            steps {
                withSonarQubeEnv(installationName: 'sonar', credentialsId: 'sonar-cred') {
                sh '/opt/maven/bin/mvn sonar:sonar'
             }
                // sh '''
                // /opt/maven/bin/mvn sonar:sonar \
                // -Dsonar.projectKey=project \
                // -Dsonar.host.url=http://18.221.255.167:9000 \
                // -Dsonar.login=36bed1c36c3b4fe570faf81a9778bd949e37c13a
                // '''
                
            }
        }
        
           stage('Quality-gate') {
            steps {
                timeout(10) {
    
            }
                waitForQualityGate true
            }
        }

        stage('deploy') {
            steps {
                echo "deploy success"
            }
        }
    }
}
