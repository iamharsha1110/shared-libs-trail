def gc(Map config = [:]) {
    def gitUrl = "https://github.com/iamharsha1110/${config.repoName}.git"
    sh """
        git clone -b ${config.branch} ${gitUrl}
        echo "Cloning done - ${config.repoName} from ${gitUrl} on branch '${config.branch}'"
    """
}

def test(Map config = [:]) {
    dir("${config.repoName}") {
        sh """
            pwd
            mvn test
        """
    }
}

def sonar(Map config = [:]) {
    withSonarQubeEnv(config.sonarserver) {
        withCredentials([string(credentialsId: config.sonartoken, variable: 'SONAR_TOKEN')]) {
            dir("${config.repoName}") {
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


def qg(Map config = [:]) {
    timeout(time: config.timeout ?: 2, unit: 'MINUTES') {
        waitForQualityGate abortPipeline: true
    }
}

def build(Map config = [:]) {
    dir("${config.repoName}") {
        sh """
            pwd
            mvn package
        """
    }
}
