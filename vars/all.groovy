def gc(Map config = [:]) {
    def gitUrl = "https://github.com/iamharsha1110/${config.repoName}.git"
    sh """
        git clone -b ${config.branch} ${gitUrl}
        echo "Cloning done - ${config.repoName} from ${gitUrl} on branch '${config.branch}'"
        pwd
        cd ${config.repoName}
        pwd
    """
}

def test(Map config = [:]) {
    sh """
        cd ${config.repoName}
        mvn test
    """
}

def sonar(Map config = [:]) {
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

def call(Map config = [:]) {
    timeout(time: config.timeout ?: 2, unit: 'MINUTES') {
        waitForQualityGate abortPipeline: true
    }
}

def build(Map config = [:]) {
    sh """
        pwd
        cd ${config.repoName}
        mvn clean package
        """
}
