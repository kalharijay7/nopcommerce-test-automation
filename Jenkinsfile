pipeline {
    agent any

    tools {
        maven 'Maven3.9'
    }

    stages {
        stage('Checkout') {
            steps {
                echo '📦 Checking out source code...'
                git branch: 'main', url: 'https://github.com/kalharijay7/nopcommerce-test-automation.git'
            }
        }

        stage('Build') {
            steps {
                echo '⚙️  Running Maven Clean and Compile...'
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                echo '🧪 Executing TestNG tests...'
                bat 'mvn test'
            }
            post {
                always {
                    echo '📁 Archiving reports and logs...'
                    junit 'target/surefire-reports/*.xml'
                    archiveArtifacts artifacts: 'logs/**, screenshots/**, allure-results/**', fingerprint: true
                }
            }
        }

//        stage('Allure Report') {
//            steps {
//                echo '📊 Generating Allure Report...'
//                allure([
//                    includeProperties: false,
//                    jdk: '',
//                    results: [[path: 'allure-results']]
//                ])
//            }
//        }
    }

    post {
		always{
			echo '📊 Generating Allure Report...'
                allure([
                    includeProperties: false,
                    jdk: '',
                    results: [[path: 'allure-results']]
                ])
            echo 'Allure Report Generation attempted.'
		}
        success {
            echo '✅ Build Successful!'
        }
        failure {
            echo '❌ Build failed — check test reports and logs.'
        }
    }
}