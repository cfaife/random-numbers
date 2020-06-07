
# Vodacom Random Number's Generator API
The system was developed using `Java Concurrency API` and `Jakarta EE 8` such as `JAX-RS`, `EJB` and `CDI` technologies.
  
#Preparing the application server 
Download   a wildfly-19  application server at: `https://wildfly.org/downloads/`unzip  and install  in your preferred folder, for example `/opt/`  folder run the script at JBOSS_HOME/bin/add-user.sh in order to create a user
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
		
#Exploring some available resources:
	
Requests a new Random number, when it completes the http response header has a key `x-request-duration` with the value  of the  process duration: 

		POST http://localhost:8080/random-numbers/api/v1/random
		
List of Generated Random Numbers:

		GET http://localhost:8080/random-numbers/api/v1/history

Cancels a Random Number Request that is pending:

		http://localhost:8080/random-numbers/api/v1/cancel/<requestId>

Get system usage statistics:

		GET http://localhost:8080/random-numbers/api/v1/stats
		
Returns the list of Random Number Requests that are pending to be processed by the system:
		
		http://localhost:8080/random-numbers/api/v1/pending
		
Changes the size of the Thread-pool used to process the Random Number Requests.Maximum supported value should be 10.Minimum value should be 1.	
		
		http://localhost:8080/random-numbers/api/v1/threads/<size-of-thread-pool>
		
		
		