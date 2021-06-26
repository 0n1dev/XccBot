pipeline {
    agent any
    stages {
        stage('Git Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Unit Test') {
            steps {
                echo 'Careers Test'

                sh 'chmod +x gradlew'      // 모든 사용자에게 gradlew 권한 추가
                sh './gradlew clean checkstyleMain' // gradlew 의 celan checkstyleTest 를 실행 
            }
        }

        stage('Build') {
            steps {
                echo 'Careers Build'

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
                                    execCommand: "ls -al" //원격지에서 실행할 커맨드
  			)
                            ]
                        )
                    ]
                )
            }

    }

    post {
        def discordURL = 'https://discord.com/api/webhooks/855816593162633246/ZN3LvWBP7tEy18zUOw55Zdpup3MtcPKik4RG3chSwEXVN0w62XS1O9__nhnsx5r08bM1'
        // URL of image png/jpg to place to right of Discord build notifications
        def discordImage = 'https://blabla.png'
        def discordDesc = "description\n"
        def discordFooter = "footer desc with vars: ${env.JOB_BASE_NAME}` (build #${BUILD_NUMBER})"
        def discordTitle = "${buildName} (devel)"
        success {
		discordSend(
			description: discordImage,
			footer: discordFooter,
			link: env.BUILD_URL,
			result: currentBuild.currentResult,
			title: discordTitle,
			webhookURL: 'https://discord.com/api/webhooks/855816593162633246/ZN3LvWBP7tEy18zUOw55Zdpup3MtcPKik4RG3chSwEXVN0w62XS1O9__nhnsx5r08bM1', 
		)
            
        }
        failure {
		discordSend(
			description: discordImage,
			footer: discordFooter,
			link: env.BUILD_URL,
			result: currentBuild.currentResult,
			title: discordTitle,
			webhookURL: 'https://discord.com/api/webhooks/855816593162633246/ZN3LvWBP7tEy18zUOw55Zdpup3MtcPKik4RG3chSwEXVN0w62XS1O9__nhnsx5r08bM1', 
		)
        }
    }

  }
}
