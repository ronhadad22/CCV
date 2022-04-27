def DF=env.BRANCH_NAME
def parallelStages = [:]

pipeline {
    agent any
    stages {
        stage('fdd'){
            steps{
                 projectsToBuild = ["hw1","hw2"]
                 projectsToBuild.each { p ->
                   parallelStages[p] = expression{ changeset "**/hw2/**"  }
 //                           sh "echo parallelStages: ${parallelStages[hw1]}"
                 }
            }//steps
        }
        stage('Example Build') {
            
            when {
                allOf{ 
                    parallelStages
                        }
            }
            steps {
 //               build(job: 'test/master', wait: false, propagate: false)
                dir('hw1') {
                sh 'echo ron' 
//                build job: 'testtt/master', parameters: [
//                string(name: 'param1', value: "value1")
//                ]

                }
            }
        }
    }
}
