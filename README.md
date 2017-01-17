# Infiquest
Stackoverflow kinda app for internal use by any organization

Pre-requistes for setting up the server:

1. Java 8
2. Logstash - 5.1.1
3. Elastic search - 5.1.1
4. Postgresql - 9.6

Install all the above prereqistes and follow the steps below to configure each of them.

Configuring Postgresql
***********************

1. Login as postgres user.
2. Run the script - createInfiquestDb :
   psql -f psql -f createDB.sql
   
Configuring elastic search
**************************
1. Modify elasticsearch.yml present under config folder of your elasticsearch installtion as per below suggestions

cluster.name: infiquestcluster elasticsearc
node.name: infiquestnode

If this server needs to be accessed by external entities(the ones not installed on localhost, like for example- logstash may be installed on some remote server and will need to be able to connect to elasticSearch), then in that case modify below entries as:
network.host: localhost
http.host : <IP of server where elasticsearch is installed.>

2. Start elasticSearch server post these changes

Configuring Logstash
*********************

1. Modify the esconfig.conf available under the root path as per the below suggestions:
   a. Modify jdbc_connection_string => "jdbc:postgresql://10.220.15.170:5432/Infiquest" to point to your DB path.
      If the db is installed on the localhost, the above IP can be replaced by localhost
   b. Modify jdbc_password with the password for the postgres user of PostgreSql .
   c. Place the postgresql-9.1-901-1.jdbc4.jar in a appropriate location on your server where logstash is installed and modify jdbc_driver_library to point to that location.
   d. Modify hosts so that it points to the server where elastic search is running.
   
 2. Based on your OS, add a cronjob so that the following command is run every few seconds:
    bin/logstash -f esconfig.conf
    
 Configuring config.yml
 *********************
 Modify the config.yml as per below suggestions:
 
 Modify password to contain password for postgres user of Postgresql.
 Modify elasticSearchHost to point to IP where elastic search is installed.
 
 
