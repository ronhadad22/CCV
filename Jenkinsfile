pipeline {
    agent any
    stages {
        stage('Example Build') {
             when {
                changeset "**/hw1/*.sh"
            }
            steps {
                dir('hw1') {
                  sh 'echo ron'    
                }
            }
        }
    }
}
