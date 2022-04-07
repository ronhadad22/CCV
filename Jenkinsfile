def DF=env.BRANCH_NAME
def BR_JOB="master"

pipeline {
    agent any

    stages {
        parallel(
            stage('StageA') {
                echo "This is branch a"
            },
            stage('StageB') {
                echo "This is branch b"
            }
          )
        stage('Example Build') {
            steps {
//                build(job: 'test/just-test', wait: false, propagate: false)
//                echo "fsdfs ${BR_JOB}"        
                build job: "testtt/${DF}", wait: true, propagate: true , parameters: [string(name: 'param1' ,value: "value1")]
                
            }//STEPS
        }//example build

        stage('FINDSF') {
            steps {
                sh 'echo ron' 
            }
        }    
    }//stages
}//pipeline
