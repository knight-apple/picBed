pipeline {
  agent {
    docker {
      image 'insideo/jre8'
      args '-p 22222:22222'
    }

  }
  stages {
    stage('') {
      steps {
        git(url: 'https://github.com/knight-apple/picBed.git', branch: 'master', changelog: true, poll: true)
      }
    }

  }
}