#!groovy

pipelineJob("python-job") {
    definition {
        cpsScm {
            lightweight(true)
            scm {
                git {
                branch("*/main")
                remote {
                    credentials("GitHub token")
                    url ("https://github.com/Dynkine/aurora")
                }
                }
            }
        }
    }
    triggers {
        githubPush()
        upstream("Seed All DSL jobs", 'SUCCESS')
    }
}
