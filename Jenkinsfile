pipeline {
    agent any   // Run on any available agent (your local Jenkins node)

    tools {
        maven 'Maven3.8.6'   // Use the name configured in Jenkins under "Global Tool Configuration"
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
                    junit 'target/surefire-reports/*.xml'   // TestNG XML reports for Jenkins
                    archiveArtifacts artifacts: 'logs/**, screenshots/**, target/**', fingerprint: true
                }
            }
        }
    }

    post {
        success {
            echo '✅ Build Successful!'
        }
        failure {
            echo '❌ Build failed — check test reports and logs.'
        }
    }
}
