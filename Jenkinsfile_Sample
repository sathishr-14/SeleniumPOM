pipeline{
    agent any
    stages{
        stage("Build"){
            steps{
                echo("Build the project")
            }
        }
         stage("Run UTs"){
            steps{
                echo("Run UTs")
            }
        }
        stage("Deploy to Dev"){
            steps{
                echo("Dev deployment")
            }
        }
        stage("Deploy to QA"){
            steps{
                echo("QA deployment")
            }
        }
        stage("Run automation reg test"){
            steps{
                echo("Run automation reg test")
            }
        }
         stage("Deploy to Stage"){
            steps{
                echo("Stage deployment")
            }
        }
         stage("Deploy to Prod"){
            steps{
                echo("Prod deployment")
            }
        }
    }
}