def DF=env.BRANCH_NAME
def BR_JOB="master"
pipeline {
    agent any
    stages {
        stage('Example Build') {
            steps {
                build(job: 'test/just-test', wait: false, propagate: false)
                echo "fsdfs ${DF}"
                dir('hw1') {
                sh 'echo ron' 
                build job: 'testtt/${BR_JOB}', parameters: [
                string(name: 'param1', value: "value1")
                ]
                }
            }
        }
    }
}
