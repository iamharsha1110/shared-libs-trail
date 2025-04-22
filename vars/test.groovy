def test(Map config = [:]) {
    dir("${config.repoName}") {
        sh """
            pwd
            mvn test
        """
    }
}
