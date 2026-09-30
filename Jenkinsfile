
pipeline{

agent {
     label 'linux && maven'
      }
 
 parameters{
   string(
      name: 'RELEASE_NOTES',
      defaultValue: 'Regular release',
      description: 'Notes for the releases'
      )
  } 

 tools{
   maven 'maven3'
   jdk 'jdk17'
 }

 environment{

   APP_NAME='invoice-service'

  }
 options{

   timeout(time: 15, unit: 'MINUTES')

  }
  
  stages{

    stage('Checkout'){
 
      steps{

        checkout scm

       }
    }

    stage('Build'){

      steps{
 
        sh 'echo "Building $APP_NAME"'
        sh 'echo "Release notes: $RELEASE_NOTES"'
        sh 'mvn -f invoice-service/pom.xml compile'

      }
    }

    stage('Test'){

      steps{

        sh 'mvn -f invoice-service/pom.xml test'

      }

        post{
     
          always{

            junit 'invoice-service/target/surefire-reports/*.xml'

            }
        }
    }


       stage('Package'){

      
       when{
         expression{
            env.GIT_BRANCH=='origin/main' 
 
         }
       }
     
 
         steps{
           
             sh 'mvn -f invoice-service/pom.xml install'
          }     

   }

     stage('Archive') {
       steps {
        archiveArtifacts artifacts: 'invoice-service/target/*.war',
                             fingerprint: true
    }
  }

     stage('Deploy') {
    when {
        expression {
            env.GIT_BRANCH == 'origin/main'
        }
    }

    steps {
        withCredentials([
            usernamePassword(
                credentialsId: 'jenkins-tomcat-deploy',
                usernameVariable: 'TOMCAT_USER',
                passwordVariable: 'TOMCAT_PASSWORD'
            )
        ]) {
            sh ''' 
             curl --fail \
             --user "$TOMCAT_USER:$TOMCAT_PASSWORD" \
             --request PUT \
             --upload-file invoice-service/target/invoice-service-1.0-SNAPSHOT.war \
             "http://172.31.0.115:9090/manager/text/deploy?path=/invoice-service&update=true"
             '''
        }
    }
}  

     stage('Verify') {
    when {
        expression {
            env.GIT_BRANCH == 'origin/main'
        }
    }

    steps {
        sh '''
            curl --fail \
            http://172.31.0.115:9090/invoice-service/
        '''
    }
}  

 
}

post {
    failure {
        mail(
            subject: "FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
            body: """
Job Name: ${env.JOB_NAME}
Build Number: ${env.BUILD_NUMBER}
Build URL: ${env.BUILD_URL}

The Jenkins build has failed.
""",
            to: "navzzzonline@gmail.com"
        )
    }

    fixed {
        mail(
            subject: "FIXED: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
            body: """
Job Name: ${env.JOB_NAME}
Build Number: ${env.BUILD_NUMBER}
Build URL: ${env.BUILD_URL}

The Jenkins build is fixed and successful again.
""",
            to: "navzzzonline@gmail.com"
        )
    }
}

  
} 

       
