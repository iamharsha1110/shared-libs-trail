def call(Map config = [:]) {
    sh """
        cd ${config.repoName}
        mvn test
    """
}
