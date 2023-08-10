def call() {
  node {
    common.checkout()
    sh 'aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 637261222008.dkr.ecr.us-east-1.amazonaws.com'
    sh 'docker build -t ${COMPONENT} .'
    sh 'docker tag ${COMPONENT}:latest 637261222008.dkr.ecr.us-east-1.amazonaws.com/${COMPONENT}:${TAG_NAME}'
    sh 'docker push 637261222008.dkr.ecr.us-east-1.amazonaws.com/${COMPONENT}:${TAG_NAME}'
  }
}