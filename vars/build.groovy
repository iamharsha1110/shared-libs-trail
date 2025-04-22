def build(Map config = [:]) {
    dir("${config.repoName}") {
        sh """
            pwd
            mvn package
        """
    }
}
