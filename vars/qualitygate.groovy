def call(Map config = [:]) {
    timeout(time: config.timeout ?: 2, unit: 'MINUTES') {
        waitForQualityGate abortPipeline: true
    }
}
