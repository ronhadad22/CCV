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
                    projectsToBuild.each { p ->
                        parallelStages[p] = {
 //                                   'triggerBuildBranch' : {  
                                        build job: "testtt/${p}", wait: false, propagate: true
 //                                   } 
                                } 
                            }
                    
                    sh "echo projectToBuild: ${parallelStages}"
                    parallel parallelStages 
                    
//                    projectsToBuild.each { p ->
//                        parallelStages[p] = {
//                            node(chosenAgent) {
//                                dir(p) {
//                                   stage(p) {
//                                        sh('make && make build')
//                                    }
//                                }
//                            }
//                        } 
//                    }    
                    
                }
            }
        }
    }
}
