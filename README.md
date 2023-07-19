# Quarkus Social API

This is a sample application built using Quarkus, Docker, Lombok, Java 17, Hibernate, and Panache. It provides a social media-like API where users can register, create posts, and follow other users.

## Prerequisites

Before running the application, make sure you have the following installed:

- Java Development Kit (JDK) 17
- Docker
- Docker Compose (optional)

## Getting Started

To get started with the Quarkus Social API, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/carloshendvpm/quarkus-social.git
   ```

2. Build the application:

   ```bash
   cd quarkus-social-api
   ./mvnw clean package
   ```

3. Build and run the Docker container:

   ```bash
   docker build -t quarkus-social-api .
   docker run -i --rm -p 8080:8080 quarkus-social-api
   ```

   Alternatively, you can use Docker Compose:

   ```bash
   docker-compose up
   ```

4. The API is now accessible at `http://localhost:8080`. You can use tools like cURL, Postman, or any REST client to interact with the API.

## API Documentation

The Quarkus Social API provides the following endpoints:

- **POST /users**: Create a new user. Requires a JSON payload with the user's details.
- **GET /users**: Get a list of all users.
- **POST /users/userId/posts**: Create a new post. Requires a JSON payload with the post's details.
- **GET /users/userId/posts**: Get a list of all posts.
- **PUT /users/{followerId}/follow/{userId}**: Start following a user.
- **DELETE /users/{followerId}/unfollow/{userId}**: Stop following a user.

## Database Configuration

The application uses an embedded H2 database by default. If you want to use a different database, you can configure it in the `application.properties` file. Update the following properties:

```properties
quarkus.datasource.jdbc.url=jdbc:<database_url>
quarkus.datasource.username=<username>
quarkus.datasource.password=<password>
```

Make sure to include the appropriate JDBC driver dependency in the application's `pom.xml` file.

## Development

If you want to make changes to the application and run it in development mode, use the following command:

```bash
./mvnw compile quarkus:dev
```

This starts the application in development mode, allowing for hot-reloading and faster development iterations.

## Contributing

If you would like to contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature/bug fix.
3. Make your changes and commit them.
4. Push your changes to your forked repository.
5. Submit a pull request describing your changes.

## Acknowledgments

This project was created as a demonstration of Quarkus and its integration with Docker, Lombok, Java 17, Hibernate, and Panache. Special thanks to the Quarkus community for their contributions and support.
