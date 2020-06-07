
# Random Number's Generator API

The  engine of  random numbers  generation is supported by Java Concurrency API. 

Download   a wildfly  application server at: `https://wildfly.org/downloads/`
unzip  and install  in your preferred folder, for example `/opt/`  folder
run the script at JBOSS_HOME/bin/add-user.sh in order to create a user
	the user should be  
		
		username: vodauser
		password: vodacom1

#  Building the code using maven
	Type the below command:
	
		mvn clean install
	
After build and compile the code, deploy the package war file to `wildfly` deployment's folder running:  	
		
		cp random-numbers/target/random-numbers.war <wildfly-folder>/standalone/deployments/
		
Startup  the server using the  below command:		
		
		<wildfly-folder>/bin/standalone.sh
		
Using the available resources:
		
		http://localhost:8080/random-numbers/api/v1/history