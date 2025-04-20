def call() {
    sh "mvn test"
    sh "mvn clean package"
}
