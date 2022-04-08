def DF=env.BRANCH_NAME

pipeline {
    agent any

    stages {
//        parallel(
//            stage('StageA') {
//                echo "This is branch a"
//            },
//            stage('StageB') {
//                echo "This is branch b"
//            }
//          )
        stage('Example Build') {
            steps {
//                build(job: 'test/just-test', wait: false, propagate: false)
//                echo "fsdfs ${BR_JOB}"
                parallel(
                    "triggerBuildBranch" : {  
                        build job: "testtt/${DF}", wait: false, propagate: true , parameters: [string(name: 'param1' ,value: "value1")] 
//                        build(testtt/${DF}", wait: true, propagate: true , parameters: [string(name: 'param1' ,value: "value1"))
                    }, 
                    "triggerBuildMaster" : {  
                        build job: "testtt/master", wait: false, propagate: true , parameters: [string(name: 'param1' ,value: "value1")] 
                    })
            }//STEPS
        }//example build
        stage('FINDSF') {
            when{
                expression {
                    !("SUCCESS".equals(currentBuild.previousBuild.result))
                }
            }
            steps {
                sh 'echo last build was failed' 
            }
        }  
        stage('CHECK-PIPE') {
            steps {
                sh 'echo sdf' 
            }
        }              
    }//stages
}//pipeline
