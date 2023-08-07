def call() {
  pipeline {

    agent {
      node { label 'workstation'}
    }

    stages {

      stage('Build') {
        steps {
          sh 'mvn package'
        }
      }

      stage('Unit tests') {
        steps {
          echo 'unit tests'
          // sh 'mvn test'
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
        steps {
          echo 'Publish a Artifact'
        }
      }

    }

  }
}