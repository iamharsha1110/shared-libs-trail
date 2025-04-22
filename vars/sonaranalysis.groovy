def call(Map config = [:]) {
    def path = "../${config.pipelinename}/${config.repoName}"
    
    withSonarQubeEnv(config.sonarserver) {
        withCredentials([string(credentialsId: config.sonartoken, variable: 'SONAR_TOKEN')]) {
            dir(path) {
                sh """
                    pwd
                    mvn clean verify -DskipTests=true sonar:sonar \\
                        -Dsonar.projectKey=${config.projectKey} \\
                        -Dsonar.projectName='${config.projectName}' \\
                        -Dsonar.token=\${SONAR_TOKEN}
                """
            }
        }
    }
}

