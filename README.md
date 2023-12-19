# MultiService-REST-SOAP-GRPC-GraphQL
This Maven module project contains two microservices developed using Spring Boot 3 and Java 17. 

The first microservice, 'Customer-Service', handles Customer management and exposes different web service protocols like REST, SOAP, GraphQL, and gRPC, each accompanied by their respective unit tests.

The second microservice, 'Customer-Service-Client,' acts as a client that consumes the web services exposed by the first microservice. It utilizes different client approaches for each service type, such as RestTemplate, WebClient, and Open Feign for Rest client, JAX-WS and cxf-codegen-plugin for SOAP client, HttpGraphQlClient and WebClient for GraphQL client, and protoc-jar-maven-plugin for gRPC client.


## Customer-Service Microservice

This microservice is responsible for managing Customer data and offers four distinct web services:

	1.	REST : This service provides a set of RESTful endpoints for customer management.
	
	2.	SOAP : It exposes a SOAP-based web service for interacting with customer data.
	
	3.	GraphQL : Offers a GraphQL API for flexible querying of customer data.
	
	4.	gRPC : It offers gRPC-based communication for high-performance, cross-language RPC calls.
	
## Testing

Unit tests are in place for each of the services, ensuring the reliability and correctness of the microservice's functionality.


## Customer-Service-Client Microservice

This microservice acts as a consumer of the web services provided by the 'Customer-Service' microservice. It employs different client libraries and approaches for communication:

	1. REST Client:
	
		⦁ RestTemplate: A programmatic client for consuming RESTful services.
		
		⦁ WebClient: Reactive programming client for REST API interactions.
		
		⦁ OpenFeign: Declarative approach to define REST clients.
		
	2. SOAP Client:
	
		⦁ JAX-WS: Utilizes JAX-WS for interacting with SOAP web services.
		
		⦁ cxf-codegen-plugin: Generates client code for SOAP service consumption.
		
	3. GraphQL Client:
	
		⦁ HttpGraphQlClient: Utilizes HTTP-based requests to query data from the GraphQL service.
		
		⦁ WebClient: Reactive approach for GraphQL interactions.
		
	4. gRPC Client:
	
		⦁ Utilizes the 'protoc-jar-maven-plugin' to generate gRPC client code for communication.


## Tools and Technologies Used

	⦁ Spring Boot 3.1.5: Spring Boot's version.
	
	⦁ Java 17: Java's version.
	
	⦁ JUnit 5: The testing framework used for writing unit tests.
	
	⦁ H2 Database: An embedded, in-memory database for data storage during development and testing.
	
	⦁ Maven: Dependency management and build tool.
	
	⦁ DTO with MapStruct: For efficient data transfer between microservices.
	
	⦁ Lombok: Reduces boilerplate code by providing annotations for common tasks.
	
	⦁ Logging with SLF4J
	
	⦁ OpenAPI: Enables the generation of API documentation for REST services.


This application leverages modern technologies and best practices to develop a microservices-based architecture, 
enabling robust customer management and consumption of these services through various client libraries. 
The provided REST, SOAP, GraphQL, and gRPC services, along with their respective clients, allow 
for flexible and efficient interactions within the system. 

