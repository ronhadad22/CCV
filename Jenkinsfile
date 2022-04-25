def DF=env.BRANCH_NAME


pipeline {
    agent any
    stages {
        stage('Example Build') {
            
            when {
                allOf{ 
                    expression{ changeset "**/hw2/**"  }
                    changeset "**/hw1/**"
                        }
            }
            steps {
                build(job: 'test/master', wait: false, propagate: false)
                echo "${{DF}}"
                dir('hw1') {
                sh 'echo ron' 
                build job: 'testtt/master', parameters: [
                string(name: 'param1', value: "value1")
                ]

                }
            }
        }
    }
}
