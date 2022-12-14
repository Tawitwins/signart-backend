## Running your UI locally

In this file are mentioned the steps to run the Signart Backend project with VSCode. 

---
## Requirements

* Install Java 8 (jdk1.8.0_151) and Maven.
* Clone the signart parent repository from http://10.42.1.120/signart/signart-backend.git.
   (if you use this call "git clone")
* Pull the latest changes with the correct branch you need to use.

## Deploy signartBackend locally with intellij

* Install intellij
* Click File → Open "SignartAdmin" (project's name)
* Clean and build the project from your IDE (or use the terminal: mvn clean, mvn verify or mvn install). The jar is now created on /target in your project directory.
* Glassfish's configuration
  - Download glassfish application server (glassfish 5.0.0)
  - Copy the postgres driver (postgresql-42.5.0.jar) on /glassfish5/glassfish/lib
  - go to intellij: Run/Edit configuration...
  - add glassfish server specifying the path on dowloaded glassfish's directory.
  - choose or create a domain with the port number (https://glassfish.org/docs/5.1.0/reference-manual/create-domain.html)
  - Run the server and go to glassfish admin console (localhost:4848  depending on the domain).
  - *** Create a connexion pool and ressources ***
  - Deploy the backend's jar : Applications/deploy

*** Create a connexion pool and ressources ***
- go to /glassfish4/bin/asadmin and run the commands: 
  + create-jdbc-connection-pool --restype=javax.sql.ConnectionPoolDataSource --datasourceclassname=org.postgresql.ds.PGConnectionPoolDataSource --property "User=signart:Password=passer123:DatebaseName=signart:ServerName=10.42.1.205:PortNumber=5432" poolSignart
  + create-jdbc-resource --connectionpoolid poolSignart jdbc/signart 
- It is up to the developer to edit the pool's properties. 




---
## Setting up pgAdmin

* Download and install pgAdmin
* Server → Create Server 
* General → Set the name as msis
* Connection → Set the host name as : 10.42.1.205 (ask for the postgres database's ipAdress if the default one doesn't work)
  - Username : signart (default)
  - password : passer123 (default)
  (ask for the credentials if the defaults one doesn't work)
_ You are still able to use a local database (localhost).