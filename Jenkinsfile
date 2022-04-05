pipeline {
    agent any
    stages {
        stage('Example Build') {
            steps {
                build(job: 'test/just-test', wait: false, propagate: false)
                
                dir('hw1') {
                sh 'echo ron' 
                build job: 'testtt/master', parameters: [
                string(name: 'param1', value: "value1")
                ]
                }
            }
        }
    }
}
