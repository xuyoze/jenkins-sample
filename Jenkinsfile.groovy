#!groovy

def deployToTomcat(localWarPath, warName) {
    def server = "192.168.87.76"
    def result = sh(
            script: "curl --upload-file ${localWarPath} 'http://${TOMCAT_ACCOUNT}@${server}/manager/text/deploy?update=true&path=/${warName}'",
            returnStdout: true
    ).trim()

    if (result.startsWith('OK')) {
        echo result
    } else {
        error result
    }
}

def deployService(localJarPath, remotePath) {
    sh "ssh 'root@localhost76' 'service demo-svc stop'"
    sh "scp ${localJarPath} root@localhost76:${remotePath}"
    sh "ssh 'root@localhost76' 'service demo-svc start'"
    sh "ssh 'root@localhost76' 'service demo-svc status'"
}

pipeline {
    agent any
    environment {
        TOMCAT_ACCOUNT = credentials('tomcat-account-test-env')
    }
    stages {

        stage('Build Test') {
            when {
                branch 'develop'
            }
            steps {
                sh 'mvn -B clean package -Dspecific -P dev'
            }
        }
        stage('Deploy Test') {
            when {
                branch 'develop'
                expression {
                    currentBuild.result == null || currentBuild.result == 'SUCCESS'
                }
            }
            parallel {
                stage('Deploy Test Api') {
                    steps {
                        deployToTomcat('demo-api/target/demo-api.war', 'demo-api')
                    }
                }
                stage('Deploy Test Web') {
                    steps {
                        deployToTomcat('demo-web/target/demo-web.war', 'demo-web')
                    }
                }
                stage('Deploy Test Svc') {
                    steps {
                        deployService('demo-svc/target/demo-svc.jar', '/var/services/demo-svc.jar')
                    }
                }

            }
        }
    }
}