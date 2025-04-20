def call(Map config = [:]) {
    withSonarQubeEnv(${config.sonarserver}) {
        withCredentials([string(credentialsId: ${config.sonartoken}, variable: 'SONAR_TOKEN')]) {
            sh """
                mvn clean verify sonar:sonar \
                    -Dsonar.projectKey=${config.projectKey} \
                    -Dsonar.projectName='${config.projectName}' \
                    -Dsonar.token=$SONAR_TOKEN
            """
        }
    }
}
