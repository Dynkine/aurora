FROM jenkins/jenkins:latest
## Disable the setup wizard
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
ENV CASC_JENKINS_CONFIG /var/jenkins_home/casc
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli -f /usr/share/jenkins/ref/plugins.txt
COPY casc/ /var/jenkins_home/casc
COPY init.groovy.d/ /var/jenkins_home/init.groovy.d
