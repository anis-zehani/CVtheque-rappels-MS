pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean install package -DskipTests -Pprod'
      }
    }

    stage('Ansible CI') {
      steps {
        sh 'sudo ansible-playbook -i /home/odix/Devops/ansible/hosts /home/odix/Devops/ansible/rappels-MS/rappels-MS-playbook-ci.yml;'
      }
    }

    stage('Ansible CD') {
      steps {
        sh 'sudo ansible-playbook -i /home/odix/Devops/ansible/hosts /home/odix/Devops/ansible/rappels-MS/rappels-MS-playbook-cd.yml;'
      }
    }

  }
}