# springsecurity
Project on spring security

It is a Spring Boot REST API application that uses Spring Security to manage authentication and authorization for different users (e.g., regular users and admins).

Users log in with username and password (using HTTP Basic Auth).
Depending on their role (ROLE_USER or ROLE_ADMIN), they can access different API endpoints.
It uses an in-memory H2 database and sets up a few users and roles on application startup.
