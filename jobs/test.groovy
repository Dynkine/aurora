#!groovy

pipelineJob("python-job") {
    definition {
        cpsScm {
            lightweight(true)
            scm {
                git {
                branch("*/main")
                remote {
                    credentials("dynkine", "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")
                    url ("https://github.com/Dynkine/aurora")
                }
                }
            }
        }
    }
    triggers {
        githubPush()
    }
}
