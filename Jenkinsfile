pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                echo 'Компиляция проекта...'
                sh '/opt/homebrew/bin/mvn compile'
            }
        }
        
        // НОВЫЙ ЭТАП (ЛР №5)
        stage('Static Analysis') {
            steps {
                echo 'Запуск статического анализа Checkstyle...'
                sh '/opt/homebrew/bin/mvn checkstyle:check'
            }
        }
        
        stage('Test') {
            steps {
                echo 'Запуск тестов...'
                sh '/opt/homebrew/bin/mvn test'
            }
        }
        
        stage('Show Dependencies') {
            steps {
                echo 'Вывод дерева зависимостей Maven...'
                sh '/opt/homebrew/bin/mvn dependency:tree'
            }
        }
        
        stage('Package') {
            steps {
                echo 'Создание JAR-файла...'
                sh '/opt/homebrew/bin/mvn package -DskipTests'
            }
        }
    }
}