#!groovy

// create an array with our two pipelines
pipelines = ["test-python"]
// JENKINS_JOB_DSL_URL = "https://github.com/Dynkine/aurora.git"
// iterate through the array and call the create_pipeline method
pipelines.each { pipeline ->
    println "Creating pipeline ${pipeline}"
    create_pipeline(pipeline)
}

// a method that creates a basic pipeline with the given parameter name
def create_pipeline(String name) {
    pipelineJob(name) {
        definition {
            cpsScm {
               lightweight(true)
               scm {
                  git {
                    branch(main)
                    remote {
                        url ("https://github.com/Dynkine/aurora.git")
                    }
                  }
                }
            }

        }
    }
}
