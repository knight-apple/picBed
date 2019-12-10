pipeline {
  agent {
    dockerfile {
      filename 'Dockerfile'
    }

  }
  stages {
    stage('error') {
      steps {
        git(url: 'https://github.com/knight-apple/picBed.git', branch: 'master', changelog: true, poll: true)
      }
    }

  }
}