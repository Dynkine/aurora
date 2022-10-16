#!groovy

pipelineJob("python-job") {
    definition {
        cpsScm {
            lightweight(true)
            scm {
                git {
                branch("*/main")
                remote {
                    url ("https://github.com/Dynkine/aurora")
                }
                }
            }
        }

    }
    triggers {
        GenericTrigger(
            causeString: "Triggered from Webhook",
            token: "unique-token-to-start-the-current-pipeline"
        )
    }
}
