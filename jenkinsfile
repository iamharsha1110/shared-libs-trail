pipeline {
    agent any
    
    options {
        withFolderProperties()
    }

    tools {
        jdk 'jdk-21'
        maven 'maven-3.8.7'
    }

    stages {
        
        stage('Git Clone') {
            steps {
                cleanWs()
                git branch: 'main', url: 'https://github.com/iamharsha1110/vprofile-project-nexus.git'
            }
        }
        
        stage('Unit Test') {
            steps {
                sh "mvn test"
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonar-server') {
                    withCredentials([string(credentialsId: 'sonarqube-token', variable: 'SONAR_TOKEN')]) {
                sh '''
                    mvn clean verify sonar:sonar \
                        -Dsonar.projectKey=PR \
                        -Dsonar.projectName="PR" \
                        -Dsonar.login=$SONAR_TOKEN
                    '''
                    }
                }
            }
        }


        stage('Quality Gate') {
            steps {
                timeout(time: 3, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        
        stage('Build') {
            steps {
                sh "mvn clean package"
            }
        }
    }
}
