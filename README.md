# jenkins-experience

some experience about jenkins

## problems

### git clone to special dir
```txt
checkout scm: [$class: 'GitSCM',extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'deploy-smac']], userRemoteConfigs: [[url: "${REPO}", credentialsId: "${credential}"]], branches: [[name: "${VERSION}"]]],poll: false
```