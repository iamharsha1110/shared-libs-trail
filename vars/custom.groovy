def call(String repoName) {
    def gitUrl = "https://github.com/iamharsha1110/${repoName}.git"
    sh """
        git clone ${gitUrl}
        cd ${repoName}
        mvn clean package -DskipTests=true
    """
}
