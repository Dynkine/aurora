pipeline {
  agent { label 'agent' }
  stages {
    stage('version') {
      steps {
        sh 'python3 --version'
      }
    }
    stage('DevOps') {
      steps {
        sh 'python3 Main.py'
      }
    }
  }
}