package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
/*
basic auth -> Spring boot applies a basic auth -> username, password ->
 end points -> /borrow -> use spring security
 users / roles -> ADMIN USER MODERATOR etc

 JWT - > compact url safe way dealing authentication secure transmit of data
 JSON WEB TOKEN -> Authentication ,Authorization , Data Exchange
 JSON -> Self contained -> has all necessary details (no server side storage)

 Representation -> 3 parts   -> ABC(....).123(...).DEF(...)
 								header.-> { metadata}
 								payload -> contains claims (data) -> userId, UserName etc
 								signature -> used to verify JWT auth
 								also the secret key -> just known to server

 FLOW -> JWT
 USER LOGINS  -> client sends the username and password to server
 				server verifies creds
 				if valid ->generates the JWT and sends it back to the client
 				client - >JWT (localStorage, sessionStorage, cookies etc)

 	When the client requests a protected site -> JWT in Auth Header
 	Server Extracts it validates
 	if valid if not expired

 in order to increase the security -> expiration time (36000)
 refreshing of token

 Why ? -> Stateless -> not storing anything on the server
 		Fast and compact
 		Cross platform


// object relational mapping -> JPA -> set of interfaces that we use to use DBs
Jpa -> requires implementation(Hiberante) ->
Hibernate -> ORM -> that implements JPA -> additonal features
@Entity
@Table(name="users")
class User{
@GeneratedValue
@ID
private Long id;
@Column(unique=true, nullable=false)
priavte String userName;
}











 */
