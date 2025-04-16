def clone&build () {
      sh """
            git clone https://github.com/iamharsha1110/vprofile-project-nexus.git"
            mvn clean package
         """
}
