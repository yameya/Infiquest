# Infiquest
This is a Stackoverflow kinda app for internal use by any organization.
At times, we come across questions that are company-specific or domain-specific and cannot be asked in general forums like Stackoverflow.
This application was developed to handle this use case. 

![Alt test](Screenshot.PNG?raw=true)

## Technology stack:

Dropwizard, Postgres Database, Angular, Elasticsearch and Docker.

## Installation:
To install this application, navigate to the root folder of this project where docker-compose.yml file resides.
Then run the below command:
docker-compose up --build

## Usage:
On the browser, navigate to http://<DOCKER_CONTAINER_IP>:9117/
