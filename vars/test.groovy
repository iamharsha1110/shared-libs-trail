def call(Map config = [:]) {
    sh "cd ${config.repoName}"
    sh "mvn test"
}
