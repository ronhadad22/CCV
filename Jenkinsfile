def parallelStages = [:]
def projectsToBuild = []
def chosenAgent = “master”

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

                    projectsToBuild.each { p ->
                        parallelStages[p] = {
                            node(chosenAgent) {
                                dir(p) {
                                    stage(p) {
                                        sh('make && make build')
                                    }
                                }
                            }
                        } 
                    }

                    parallel parallelStages
                }
            }
        }
    }
}
