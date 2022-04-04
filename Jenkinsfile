pipeline {
    agent { docker 'maven:3.8.1-adoptopenjdk-11' } 
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
