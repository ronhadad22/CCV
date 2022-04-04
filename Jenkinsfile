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

                }
            }
            'job test' : { build job: 'job test'; }
        }
    }
}
