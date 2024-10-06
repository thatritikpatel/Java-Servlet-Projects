# Java Web Application

A comprehensive web application built with Java Servlets, Apache Tomcat, and JavaScript. This project demonstrates a complete web application architecture with a Java backend, dynamic frontend, and MySQL database integration.

## 🚀 Tech Stack

### Backend
- Java 11
- Apache Tomcat 9.0
- Servlets API 4.0
- JDBC for MySQL connectivity
- MySQL 8.0

### Frontend
- HTML5/CSS3
- JavaScript (ES6+)
- Bootstrap 5
- jQuery 3.6
- AJAX for asynchronous operations

### Development Tools
- Eclipse IDE/IntelliJ IDEA
- MySQL Workbench
- Git
- Postman for API testing

## 📁 Project Structure
```
WebContent/
├── META-INF/
│   └── MANIFEST.MF
├── WEB-INF/
│   ├── lib/
│   │   ├── mysql-connector-j-8.0.33.jar
│   │   ├── json-20231013.jar
│   │   ├── jstl-1.2.jar
│   │   └── commons-dbcp2-2.9.0.jar
│   ├── classes/
│   │   └── com/
│   │       └── example/
│   │           ├── controllers/
│   │           ├── models/
│   │           ├── dao/
│   │           └── utils/
│   └── web.xml
├── css/
│   └── style.css
├── js/
│   ├── main.js
│   └── api.js
├── images/
├── pages/
│   ├── login.jsp
│   ├── register.jsp
│   └── dashboard.jsp
└── index.jsp

src/
├── com/
│   └── example/
│       ├── controllers/
│       │   ├── UserServlet.java
│       │   └── AuthServlet.java
│       ├── models/
│       │   └── User.java
│       ├── dao/
│       │   └── UserDAO.java
│       ├── utils/
│       │   └── DBUtil.java
│       └── services/
│           └── UserService.java
└── resources/
    └── db.properties
```

## 🛠️ Setup and Installation

### Prerequisites
1. Install Java Development Kit (JDK) 11 or higher
2. Install Apache Tomcat 9.0
3. Install MySQL 8.0

### Required Libraries
Download and add these JARs to `WebContent/WEB-INF/lib/`:
1. [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)
2. [JSON-java](https://github.com/stleary/JSON-java/releases)
3. [JSTL 1.2](https://mvnrepository.com/artifact/javax.servlet/jstl/1.2)
4. [Apache Commons DBCP](https://commons.apache.org/proper/commons-dbcp/download_dbcp.cgi)

### Database Setup
1. Create MySQL database:
```sql
CREATE DATABASE webapp_db;
USE webapp_db;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Project Setup
1. Clone the repository:
```bash
git clone https://github.com/yourusername/webapp.git
cd webapp
```

2. Configure database connection:
Create `src/resources/db.properties`:
```properties
db.url=jdbc:mysql://localhost:3306/webapp_db
db.username=your_username
db.password=your_password
db.driver=com.mysql.cj.jdbc.Driver
```

3. Set up in Eclipse:
   - File -> Import -> Existing Projects into Workspace
   - Select the project directory
   - Ensure Tomcat server is configured in Eclipse
   - Add all required JARs to build path

### Building and Deployment

#### Manual Build Process
1. Compile Java classes:
```bash
# Create classes directory if it doesn't exist
mkdir -p WebContent/WEB-INF/classes

# Compile Java files
javac -d WebContent/WEB-INF/classes -cp "WebContent/WEB-INF/lib/*:$CATALINA_HOME/lib/servlet-api.jar" src/com/example/**/*.java
```

2. Create WAR file:
```bash
cd WebContent
jar -cvf ../webapp.war *
```

3. Deploy to Tomcat:
- Copy `webapp.war` to Tomcat's `webapps` directory
- Start Tomcat server

#### Eclipse Deployment
1. Right-click project -> Export -> WAR file
2. Choose destination and export
3. Copy WAR to Tomcat's `webapps` directory

## 🚦 Running the Application

1. Start MySQL server
2. Start Tomcat server:
```bash
# Windows
%CATALINA_HOME%\bin\startup.bat

# Linux/Mac
$CATALINA_HOME/bin/startup.sh
```
3. Access the application:
```
http://localhost:8080/webapp
```

## 📋 API Documentation

### User Management API

#### Create User
```
POST /api/users
Content-Type: application/json

{
    "username": "john_doe",
    "email": "john@example.com",
    "password": "securepassword123"
}
```

#### Get User
```
GET /api/users/{id}
```

#### Update User
```
PUT /api/users/{id}
Content-Type: application/json

{
    "email": "newemail@example.com"
}
```

#### Delete User
```
DELETE /api/users/{id}
```

## 🔒 Security Configuration

### web.xml Security Settings
```xml
<security-constraint>
    <web-resource-collection>
        <web-resource-name>Secure Pages</web-resource-name>
        <url-pattern>/secure/*</url-pattern>
    </web-resource-collection>
    <auth-constraint>
        <role-name>user</role-name>
    </auth-constraint>
</security-constraint>
```

## 📝 Frontend Development

### JavaScript Structure
```javascript
// api.js
const API = {
    baseUrl: '/webapp/api',
    
    async getUsers() {
        const response = await fetch(`${this.baseUrl}/users`);
        return response.json();
    },
    
    // Other API methods
};

// main.js
document.addEventListener('DOMContentLoaded', () => {
    // Initialize application
});
```

## 🚀 Deployment Checklist

1. Database Configuration
   - Update database credentials
   - Run all database migrations
   - Back up existing data

2. Application Configuration
   - Update `db.properties`
   - Configure logging
   - Set security parameters

3. Server Configuration
   - Set up SSL/TLS
   - Configure Tomcat resources
   - Set up load balancing (if needed)

## 🔧 Troubleshooting

Common Issues and Solutions:

1. ClassNotFoundException
   - Verify all required JARs are in WEB-INF/lib
   - Check build path configuration

2. Database Connection Issues
   - Verify MySQL service is running
   - Check database credentials
   - Confirm JDBC driver is present

3. 404 Errors
   - Check web.xml mappings
   - Verify URL patterns
   - Confirm file locations

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit changes
4. Push to branch
5. Open a Pull Request


## 👥 Authors

* **Ritik Patel** -

## 📞 Contact

Your Name - [@yourtwitter](https://twitter.com/yourtwitter) - email@example.com

Project Link: [https://github.com/yourusername/webapp](https://github.com/yourusername/webapp)
