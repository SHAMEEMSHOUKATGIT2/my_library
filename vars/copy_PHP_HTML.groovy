// Copy Application files into the deployable folder of the Application Server.
// Declare and assign the variables in the Jenkinsfile of the appication repo.

def copyfile(){
  echo "Coping Application files"
  sh '''
    echo "Copying Files to ${environ} Server"
  '''
  if (env.environ == "dev"){
      sh '''
        rsync -avzh ${WORKSPACE}/* --exclude-from 'exclude-list.txt' ${userid}@${server}:${deployPath}${AppName}
      '''
  }else if(env.environ == "prod")
  {
      sh '''
        rsync -avh -e "ssh -p 65002" ${WORKSPACE}/* --exclude-from 'exclude-list.txt' ${userid}@${server}:${deployPath}
      '''      
  }
}
