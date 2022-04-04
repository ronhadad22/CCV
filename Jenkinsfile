pipeline {
    agent any
    stages {
        stage('Example Build') {
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
