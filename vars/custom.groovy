def call(Map config = [:]) {
    def gitUrl = "https://github.com/iamharsha1110/${config.repoName}.git"
    sh """
        git clone -b ${config.branch} ${gitUrl}
        cd ${repoName}
        mvn clean compile
        echo "Cloning done - ${config.repoName} from ${gitUrl} on branch '${config.branch}'"
    """
}
