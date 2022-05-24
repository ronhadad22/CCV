pipeline {
    agent any
    parameters { choice(name: 'CHOICES', choices: ['one', 'two', 'three'], description: '') }
    stages {
        stage('Example Build') {
            steps {
                sh 'echo ron'
            }
        }
    }
}
