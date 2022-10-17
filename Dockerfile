FROM jenkins/jenkins:latest

USER root
RUN groupadd docker && \
    curl -fsSL https://get.docker.com -o get-docker.sh && \
    sh get-docker.sh && \
    usermod -aG root jenkins && \
    usermod -aG docker jenkins
USER jenkins

## Disable the setup wizard
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
ENV CASC_JENKINS_CONFIG /var/jenkins_home/casc
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli -f /usr/share/jenkins/ref/plugins.txt
COPY casc/ /var/jenkins_home/casc
COPY init.groovy.d/ /var/jenkins_home/init.groovy.d
