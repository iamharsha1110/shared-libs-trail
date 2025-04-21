def call(Map config = [:]) {
    sh """
        pwd
        cd ${config.repoName}
        mvn clean package
        """
}
