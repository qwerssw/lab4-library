pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                echo 'Компиляция проекта...'
                sh 'mvn compile'
            }
        }
        
        stage('Test') {
            steps {
                echo 'Запуск тестов...'
                sh 'mvn test'
            }
        }
        

        stage('Show Dependencies') {brew services start jenkins-lts
            steps {
                echo 'Вывод дерева зависимостей Maven...'
                sh 'mvn dependency:tree'
            }
        }
        
        stage('Package') {
            steps {
                echo 'Создание JAR-файла...'
                sh 'mvn package -DskipTests'
            }
        }
    }
}