pipeline {
    agent any

    environment {
        GIT_CREDENTIALS = '737a7c4c-9b2b-4f1a-9ac6-db6ff247fce6'
        KUBECONFIG_CREDENTIALS = '48ad5a71-1a3a-4850-ac3c-de03f575dc5b'
        GIT_URL = 'https://github.com/Noubissie237/Banking-System-Backend'
        BRANCH_NAME = 'main'
    }

    stages {
        stage('Clone Repository') {
            steps {
                echo 'Cloning repository...'
                git branch: env.BRANCH_NAME, credentialsId: env.GIT_CREDENTIALS, url: env.GIT_URL
            }
        }
        stage('Installation de kubectl') {
            steps {
                sh 'sudo snap install kubectl --classic'
            }
        }
        stage('Installation de kubelet') {
            steps {
                sh 'sudo snap install kubelet --classic'
            }
        }
        stage('Démarrage de minikube') {
            steps {
                sh 'minikube start'
            }
        }
        stage('Configuration de kubectl pour utiliser le cluster minikube') {
            steps {
                sh 'kubectl config use-context minikube'
            }
        }
        stage('Création du namespace') {
            steps {
                sh 'kubectl apply -f namespace.yaml'
            }
        }
        stage('Lancement du deploiement') {
            steps {
                sh 'kubectl apply -f kubernetes.yaml'
            }
        }
        stage('Lancement des services') {
            steps {
                sh 'kubectl apply -f services.yaml'
            }
        }
        stage('Vérification des pods') {
            steps {
                sh 'kubectl get pods -n banking-system-app'
            }
        }
        stage('Vérification des services') {
            steps {
                sh 'kubectl get services -n banking-system-app'
            }
        }
        stage('Vérification globale') {
            steps {
                sh 'kubectl get all -n banking-system-app'
            }
        }
    }

}
