def call(String repoName, String branch = 'main') {
    def gitUrl = "https://github.com/iamharsha1110/${repoName}.git"
    sh """
        git clone -b ${branch} ${gitUrl}
        cd ${repoName}
        mvn clean compile
        echo "Cloning done - ${repoName} from ${gitUrl} on branch '${branch}'"
    """
}
