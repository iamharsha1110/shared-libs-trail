def call() {
    sh """
        pwd
        cd ${config.repoName}
        mvn clean package
        """
}
