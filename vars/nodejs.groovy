def call() {
  pipeline {

    agent {
      node { label 'workstation'}
    }

    stages {

      stage('Build') {
        steps {
          sh 'npm install'
        }
      }

      stage('Unit tests') {
        steps {
          echo 'unit tests'
          // sh 'npm test'
        }
      }

      stage('Code Analysis') {
        steps {
          echo 'sonar'
        }
      }

      stage('Security Scans') {
        steps {
          echo 'Security Scans'
        }
      }

      stage('Publish a Artifact') {
        when {
          expression {
            env.TAG_NAME ==~ ".*"
          }
        }
        steps {
          echo 'Publish a Artifact'
          sh 'env'
        }
      }


    }

  }


}