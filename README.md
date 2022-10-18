# Setting up Jenkins server

- ## Prerequisites
    - ### Setting up connection between Github and local Jenkins installation
        
        To get Github webhooks working we need to create a connection between internet (Github server) and Jenkins server that is deployed locally and doesn’t have public IP address or DNS record. In order to solve this issue I would use the tool called [ngrok](https://ngrok.com/). It allows to create a tunnel between Github server and our local Jenkins installation.
         I’ve already created an agent for my installation so you can use mine auth-token. To use ngrok please do the following. Alternatively you can register and get your personal token.
        
        ```bash
        #Install ngrok. Here is the link to the download page. Please select your OS and follow the installation instructions.
        https://ngrok.com/download
        # authenticate
        ngrok config add-authtoken 2GCxEcxZVg0c2ytTC9q5HpM0rgf_6ckcgVG1eCmhMTAp84JuN
        # start ngrok pointing to local Jenkins at port 8080
        ngrok http 8080 > /dev/null &
        # you can find an external host address at
        http://localhost:4040
        # or if you logged in use ngrok dashboard
        https://dashboard.ngrok.com/tunnels/agents
        ```
        
    - ### GitHub webhook configuration
        
        Setup Github [webhook](https://docs.github.com/en/developers/webhooks-and-events/webhooks/creating-webhooks). Please use the external host address you received in the previous step in ngrok. The webhook Payload URL should be like the following example:
        
        ```bash
        https://github.com/Dynkine/aurora/settings/hooks/384275738
        https://79ed-2a0d-6fc2-10f8-db00-5cf4-a0dd-e075-bd.eu.ngrok.io/github-webhook/
        ```
        
    - ### Exporting env variables
        
        I use a number of secrets that I provide as env vars: jenkins admin password, agent ssh keys and  github token. You have to set up these env vars in your environment.
        
        ```bash
        # Create ssh key pair for Jenkins agent. This command uses default ~/.ssh path. Please change it if required
        ssh-keygen -t rsa -f jenkins_agent
        # Export env vars
        # Export agent's ssh private key. Please update the key path if it's not the default one.
        export EXPORTED_JENKINS_AGENT_PRIVATE_SSH_KEY=$(cat ~/.ssh/jenkins_agent)
        # Setup Jenkins admin password that you chose.
        export EXPORTED_JENKINS_ADMIN_PASSWORD=your_jenkins_admin_password
        # Setup GitHub token to allow the pipeline pull the repo. Update the token if you'd use any other than my repo.
        export EXPORTED_GITHUB_TOKEN=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
        # Export agent's ssh public key. Please update the key path if it's not the default one.
        export EXPORTED_JENKINS_AGENT_SSH_PUBKEY=$(cat ~/.ssh/jenkins_agent.pub)
        ```
        
- ## Start the server
    ### Clone the repository
    
    ```bash
    git clone https://github.com/Dynkine/aurora.git
    # the "aurora" folder will be created
    ```
    
    ### Navigate to the the cloned repo
    
    ```bash
    cd aurora/
    ```
    
    ### Start the Jenkins server
    
    ```bash
    docker-compose up -d
    ```
