def call() {
    sh "cd ${config.repoName}"
    sh "mvn test"
}
