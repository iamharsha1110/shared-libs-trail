def call(String repoName, String branch) {
    def gitUrl = "https://github.com/iamharsha1110/${repoName}.git"
    sh """
        git clone -b ${branch} ${gitUrl}
        cd ${repoName}
        mvn clean package
    """
}
