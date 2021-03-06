# jenkins-experience

some experience about jenkins

## problems

### git clone to special dir
```groovy
checkout scm: [$class: 'GitSCM',extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'deploy-smac']], userRemoteConfigs: [[url: "${REPO}", credentialsId: "${credential}"]], branches: [[name: "${VERSION}"]]],poll: false
```

### read ini config
```groovy
def call(String file) {
    def m = [:]
    def lines = new File(file).readLines()
    for(line in lines) {
        def kv = line.split("=")
        if(kv.length == 2) {
            def k = kv[0]
            def v = kv[1]
            m[k] = v
        }
    }
    return m
}
```

### list git remote tags 
```groovy
def call(String git_url, String credentialsId = "gitlab") {
    def GIT_USER = ""
    def GIT_PW = ""
    withCredentials([usernamePassword(credentialsId: "${credentialsId}", passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
            def u = URLEncoder.encode(GIT_USERNAME,'UTF-8')
            def p = URLEncoder.encode(GIT_PASSWORD,'UTF-8')
            GIT_USER = u
            GIT_PW = p
    }
    git_url = git_url.replace("//","//${GIT_USER}:${GIT_PW}@")
    def gettags = ("git ls-remote -t -h ${git_url}").execute()
    return gettags.text.readLines().collect { 
        it.split()[1].replaceAll('refs/heads/', '').replaceAll('refs/tags/', '').replaceAll("\\^\\{\\}", '')
    }
}
```