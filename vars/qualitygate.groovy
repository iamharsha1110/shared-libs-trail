def call(Map config = [:]) {
    timeout(time: ${config.timeout}, unit: 'MINUTES') {
        waitForQualityGate abortPipeline: true
    }
}
