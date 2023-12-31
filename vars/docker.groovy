def call() {
  node('workstation') {

    stage('Code Checkout') {

      sh 'find . | grep "^./" |xargs rm -rf'

      if(env.TAG_NAME ==~ ".*") {
        env.gitbrname = "refs/tags/${env.TAG_NAME}"
      } else {
        env.gitbrname = "${env.BRANCH_NAME}"
      }
      checkout scm: [$class: 'GitSCM', userRemoteConfigs: [[url: "https://github.com/janardhanReddy-B/${env.component}"]], branches: [[name: gitbrname]]], poll: false

    }

    stage('Publish a Artifact') {
      sh 'docker build -t ${component} .'
      sh 'docker tag ${component}:latest 637261222008.dkr.ecr.us-east-1.amazonaws.com/${component}:${TAG_NAME}'
      sh 'aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 637261222008.dkr.ecr.us-east-1.amazonaws.com'
      sh 'docker push 637261222008.dkr.ecr.us-east-1.amazonaws.com/${component}:${TAG_NAME}'
    }
  }

}