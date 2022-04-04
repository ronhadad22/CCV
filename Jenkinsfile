pipeline {
    agent any
    stages {
        stage('Example Build') {
            build(job: 'test', propagate: true, wait: true)
            when {
                changeset "**/hw1/**"
            }
            steps {
                dir('hw1') {
                  sh 'echo ron' 
                build job: 'test', parameters: [
                string(name: 'param1', value: "value1")
                ]

                }
            }
        }
    }
}
