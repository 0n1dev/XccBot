pipeline {
    agent any
    environment {
        gitAuthor = ""
        gitMessage = ""
        envDiscordUrl = "${env.DISCORD_URL}"
        // URL of image png/jpg to place to right of Discord build notifications
        discordImage = 'https://www.rundeck.com/hubfs/jenkinsrundeck.png'
        discordDesc = "notes: Hey, the build is done!"
        discordFooter = "footer desc with vars: ${env.JOB_BASE_NAME}` (build #${BUILD_NUMBER})"
        discordTitle = "${env.JOB_BASE_NAME}"
        discordFaildDesc = "notes : Hey, the build is faild!"
    }
    stages {
        stage('Git Checkout') {
            steps {
                script {
                    def commit = sh(returnStdout: true, script: 'git rev-parse HEAD')
                    gitAuthor = sh(returnStdout: true, script: "git --no-pager show -s --format='%an' ${commit}").trim()
                    gitMessage = sh(returnStdout: true, script: 'git log -1 --pretty=%B').trim()
                }
                checkout scm
            }
        }

        stage('Convension') {
            steps {
                echo 'Convension'

                sh 'chmod +x gradlew'      // 모든 사용자에게 gradlew 권한 추가
                sh './gradlew clean checkstyleMain' // gradlew 의 celan checkstyleTest 를 실행
            }
        }

        stage('Test') {
            steps {
                echo 'Test'

                sh 'chmod +x gradlew'      // 모든 사용자에게 gradlew 권한 추가
                sh './gradlew clean test' // gradlew 의 Test 를 실행

                step $class: 'JUnitResultArchiver', testResults: '**/TEST-*.xml'
            }
        }

        stage('Build') {
            steps {
                echo 'Build'

                sh './gradlew build -x test' // gradlew build
            }
        }
        /*************** Pulish Over SSH Plug in사용******************/
        stage('SSH transfer') {
            steps([$class: 'BapSshPromotionPublisherPlugin']) {
                sshPublisher(
                        continueOnError: false, failOnError: true,
                        publishers: [
                                sshPublisherDesc(
                                        configName: "Test3",//Jenkins 시스템 정보에 사전 입력한 서버 ID
                                        verbose: true,
                                        transfers: [
                                                sshTransfer(
                                                        sourceFiles: "build/libs/*", //전송할 파일
                                                        removePrefix: "build/libs", //파일에서 삭제할 경로가 있다면 작성
                                                        remoteDirectory: "buildfile", //배포할 위치
                                                        execCommand: "nohub sh /home/test3/restart.sh &" //원격지에서 실행할 커맨드
                                                )
                                        ]
                                )
                        ]
                )
            }
        }
    }

    post {
        success {
            discordSend(
                    description: "**Status**: ${currentBuild.currentResult}\n**Build**: ${currentBuild.number}\n\n**Last Commit**: ${gitAuthor} - ${gitMessage}\n\n**Test**:\n${getTestSummary()}\n\n**Changes**:\n${getChangeString()}",
                    title: discordTitle,
                    footer: discordFooter,
                    link: env.BUILD_URL,
                    result: currentBuild.currentResult,
                    webhookURL: envDiscordUrl,
                    successful: currentBuild.resultIsBetterOrEqualTo('SUCCESS'),
            )
        }
        failure {
            discordSend(
                    description: "**Status**: ${currentBuild.currentResult}\n**Build**: ${currentBuild.number}\n\n**Last Commit**: ${gitAuthor} - ${gitMessage}\n\n***Test**:\n${getTestSummary()}\n\n**Changes**:\n${getChangeString()}\n\n${getFailedTests()}\n${currentBuild.rawBuild.getLog(10).join('\n')}",
                    title: discordTitle,
                    footer: discordFooter,
                    link: env.BUILD_URL,
                    result: currentBuild.currentResult,
                    webhookURL: envDiscordUrl
            )
        }
    }
}

import hudson.tasks.test.AbstractTestResultAction
import hudson.tasks.junit.CaseResult

@NonCPS
def getTestSummary() {
    def testResultAction = currentBuild.rawBuild.getAction(AbstractTestResultAction.class)
    def summary = ""

    if (testResultAction != null) {
        total = testResultAction.getTotalCount()
        failed = testResultAction.getFailCount()
        skipped = testResultAction.getSkipCount()

        summary = "Passed: " + (total - failed - skipped)
        summary = summary + (", Failed: " + failed)
        summary = summary + (", Skipped: " + skipped)
    } else {
        summary = "No tests found"
    }
    return summary
}

@NonCPS
def getFailedTests() {
    def testResultAction = currentBuild.rawBuild.getAction(AbstractTestResultAction.class)
    def failedTestsString = ""

    if (testResultAction != null) {
        failedTestsString = "```"
        def failedTests = testResultAction.getFailedTests()

        if (failedTests.size() > 9) {
            failedTests = failedTests.subList(0, 8)
        }

        for(CaseResult cr : failedTests) {
            failedTestsString = failedTestsString + "${cr.getFullDisplayName()}:\n${cr.getErrorDetails()}\n\n"
        }
        failedTestsString = failedTestsString + "```"
    }
    return failedTestsString
}

@NonCPS
def getChangeString() {
    MAX_MSG_LEN = 100
    def changeString = ""

    echo "Gathering SCM changes"
    def changeLogSets = currentBuild.changeSets
    for (int i = 0; i < changeLogSets.size(); i++) {
        def entries = changeLogSets[i].items
        for (int j = 0; j < entries.length; j++) {
            def entry = entries[j]
            truncated_msg = entry.msg.take(MAX_MSG_LEN)
            changeString += " - ${truncated_msg} [${entry.author}]\n"
        }
    }

    if (!changeString) {
        changeString = " - No new changes"
    }
    return changeString
}