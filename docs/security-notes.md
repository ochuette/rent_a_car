
Spring Security Notes

Question 1: Why must `/api/admin/**` be declared before the broader `/api/**` rule?

Answer: Spring Security checks the request rule in the order they are defined and applies the matching rule. The admin rule must come first so that only users with the ADMIN role can access administrative endpoints.


Question 2: What is the difference between 401 Unauthorized and 403 Forbidden?

Answer: A 401 Unauthorized response means the user is not authenticated or has not provided valid login details. A 403 Forbidden response means the user is already authenticated but does not have the required permission to access the resource.


Question 3: Why are passwords encoded with BCrypt instead of stored as plain text?

Answer: BCrypt is used to hash(#) passwords before storing them. This protects users if the database is compromised.


Question 4: Why are in-memory users suitable for this exercise but not production?

Answer: In-memory users are suitable for this exercise because they are simple to configure and do not require a database to store user information whilst, they are not suitable for production because user details are lost when the application restarts and they do not provide proper long-term user management.


Question 5: What would be needed to load users and roles from MySQL?

Answer: We would need to create database tables to store users and their roles. We would also need to create a repository and a service to retrieve user details from MySQL using the username.