# "Tour Whiz" - A Tourist Venue Guide Web Application


## Description

This project is a Spring Boot application, built and ran with Maven.

It is a RESTful API service, which allows users to review different types of venues in different cities,
filter them through certain criteria and leave reviews and ratings, or simply explore them as a venue content.


## Features

User, Venue, Social Media and Review management: the application's users are able to sign-up, login and update
their accounts. They can also add new venues and filter existing ones by different criteria, 
update their information and social media webpages and contacts, and leave and update (their personal) reviews,
by adding comments and ratings. All of these functionalities can only be applied given that certain conditions are
met, such as registering with a non-existing username or email, adding new venues only if such do not already exist
at the specific address etc.


## Tech Stack

- Spring Boot
- Microsoft SQL Server
- Bearer Authentication with JWTs
- H2 In-Memory Database (used only for Integration Tests)


## Setup

1. Clone this repository:

2. Create the SQL Server database and execute the provided script found in the classpath (`schema.sql`) in order to create
the necessary tables. 

3. Provide the environment variables required for the configuration of the database and security.


## Usage

_The application is hosted on localport:8080, if not specified otherwise._


1. Register an account at "/users/signup"

2. Login with your account at "/users/login" and if successful, retrieve the JWT Bearer authentication token.

3. Use the token to access the other application endpoints

4. Use the "/actuator/**" endpoints for monitoring purposes, gathering metrics etc.
