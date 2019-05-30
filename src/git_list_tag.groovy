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