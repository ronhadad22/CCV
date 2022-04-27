def DF=env.BRANCH_NAME
def parallelStages = [:]
def projectsToBuild = []

pipeline {
    agent any
    stages {
        stage('fdd'){
            steps{
                script {
                 projectsToBuild = ["hw1","hw2"]
                 projectsToBuild.each { p ->
                     parallelStages[p] =  changeset "**/${p}/**"  
 //                           sh "echo parallelStages: ${parallelStages[hw1]}"
                 }
              }
            }//steps
        }
        stage('Example Build') {
            
            when {
                allOf{ 
                    script{
//                        expression{env.BRANCH_NAME == 'master'}
                        parallel parallelStages  
                    }
                }
            }
            steps {
 //               build(job: 'test/master', wait: false, propagate: false)
                dir('hw1') {
                sh 'echo ron'
                    sh "echo ${DF}"
//                build job: 'testtt/master', parameters: [
//                string(name: 'param1', value: "value1")
//                ]

                }
            }
        }
    }
}
