def call(Map config = [:]) {
    withSonarQubeEnv(config.sonarserver) {
        withCredentials([string(credentialsId: config.sonartoken, variable: 'SONAR_TOKEN')]) {
            sh """
               cd ..
               cd ${config.pipelinename}
               cd ${config.repoName}
               pwd
                mvn clean verify -DskipTests=true sonar:sonar \
                    -Dsonar.projectKey=${config.projectKey} \
                    -Dsonar.projectName='${config.projectName}' \
                    -Dsonar.token=$SONAR_TOKEN
            """
        }
    }
}
