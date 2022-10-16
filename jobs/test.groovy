#!groovy

pipelineJob("python-job") {
    definition {
        cpsScm {
            lightweight(true)
            scm {
                git {
                branch("*/main")
                remote {
                    url ("https://github.com/Dynkine/aurora.git")
                }
                }
            }
        }
    }
    triggers {
        githubPush()
    }
}
