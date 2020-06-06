
The  engine of  random numbers  generation is supported by Java Concurrency API. 

Download   a wildfly  application server at:
unzip  and install  in your preferred folder, for example /opt/  folder
run the script at JBOSS_HOME/bin/add-user.sh in order to create a user
	the user should be  
		username: vodauser
		password: vodacom1
	
After  that compile the app using the following command	
		
		mvn clean install wildfly:deploy -Pwildfly-runtime