def parallelStages = [:]
def projectsToBuild = []
//def chosenAgent = “master”
def DF=env.BRANCH_NAME

pipeline { 
    agent any 
    stages { 
        stage("Compile & Build Binary") { 
            steps {
                script {
                    // Find directories (for simplicity’s sake, all directories)
                    def files = findFiles()
                    files.each { f ->
                        if (f.directory) {
                            projectsToBuild.add(f.name)
                        }
                    }                    
                    sh "echo projectToBuild: ${projectsToBuild}"
                    
                    projectsToBuild = ["parallel-new-syntax","parallel-pipeline","master"]
                    projectsToBuild.each { p ->
                        parallelStages[p] = {
 //                                   'triggerBuildBranch' : {  
                                        build job: "testtt/${p}", wait: true, propagate: true
 //                                   } 
                                }
 //                           sh "echo parallelStages: ${parallelStages[hw1]}"
                            }
                    
 //                   sh "echo parallelStages: ${parallelStages}"
                    parallel parallelStages 
//                    failFast true
                    
                }
            }
        }
    }
}
