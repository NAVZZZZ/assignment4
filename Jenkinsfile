
pipeline{

agent any
 
 parameters{
   string(
      name: 'RELEASE_NOTES',
      defaultvalue: 'Regular release',
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

 
}

   post{
     success{

          sh 'echo "The pipeline was successful"'
             }
       
     failure{

          sh 'echo "The pipeline failed"'
        }
    } 
  
 } 

       
