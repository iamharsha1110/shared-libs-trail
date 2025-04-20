def call(Map config = [:]) {
    def gitUrl = "https://github.com/iamharsha1110/${config.repoName}.git"
    sh """
        git clone -b ${config.branch} ${gitUrl}
        cd ${config.repoName}
        mvn clean verify
        echo "Cloning done - ${config.repoName} from ${gitUrl} on branch '${config.branch}'"
    """
}
