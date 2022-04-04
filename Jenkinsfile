pipeline {
    agent any
    stages {
        stage('Example Build') {
            
            when {
                changeset "**/hw1/**"
            }
            steps {
                build(job: 'test/master', propagate: true)
                
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
