def DF=env.BRANCH_NAME
def BR_JOB="master"

pipeline {
    agent any
    stages {
        stage('Example Build') {
            steps {
//                build(job: 'test/just-test', wait: false, propagate: false)
                echo "fsdfs ${BR_JOB}"
                dir('hw1') {
                sh 'echo ron' 
                build job: "testtt/${DF}",wait: false, parameters: [string(name: 'param1', value: "value1")]
                }
            }//STEPS
        }//example build
        stage('FINDSF') {
            steps {
                sh 'echo ron' 
            }
        }
    }//stages
}//pipeline
