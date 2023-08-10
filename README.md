# Product Management System with OAuth

This repository contains a Product Management System web application with OAuth authentication. The application is designed for managing products, categories, user registration, and more.

## Features

- **User Authentication**: Secure user registration and login using OAuth2 with Google authentication.

- **Product Management**: Manage products, and categories, and view product details.

- **Role-Based Access Control**: Users are assigned roles that define their access rights.

- **Error Handling**: Custom error pages for different HTTP status codes.

- **Responsive Design**: The application is built using Bootstrap for a responsive and visually appealing user interface.

## Tech Stack

- Spring Boot
- Thymeleaf (for HTML templates)
- Spring Security (OAuth2)
- MySQL (Database, can be switched to MongoDB)
- Bootstrap (Frontend framework)

## How to Run

1. **Clone the Repository**: 
   ```bash
   git clone https://github.com/your-username/ProductManagementSystemWithOAuth.git
   cd ProductManagementSystemWithOAuth
   ```

2. **Database Configuration**: Configure the database connection in the `application.properties` file. You can choose between MySQL and MongoDB based on your preference.

3. **Run the Application**:
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the Application**:
   The application will be accessible at [http://localhost:8080](http://localhost:8080).

5. **Login with Google**: Click on the "Login" button in the navigation bar to log in using your Google account.

6. **Explore and Manage Products**: Navigate to the admin section to manage products and categories.

## Contributions

Contributions are welcome! If you find any issues or have improvements to suggest, please feel free to create a pull request or open an issue.
