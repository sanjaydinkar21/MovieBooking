## How to Run This Project Locally

1. **Clone the Repository**:
    ```sh
    git clone <your-github-repo-link>
    cd <your-repo-directory>
    ```

2. **Ensure Java and Maven are Installed**:
    - Java JDK 17 or later
    - Maven

3. **Set Up Environment Variables**:
    - Ensure `JAVA_HOME` is set correctly and added to PATH.

4. **Build the Project**:
    ```sh
    mvn clean install
    ```

5. **Run the Application**:
    ```sh
    mvn spring-boot:run
    ```

6. **Access the Application**:
    - Go to `http://localhost:8000` in your web browser.

## Database Information

- The application uses an in-memory H2 database, configured in `src/main/resources/application.properties`.
- **H2 Console**: Access the H2 console at `http://localhost:8000/h2-console` using:
  - **JDBC URL**: `jdbc:h2:mem:testdb`
  - **User Name**: `sa`
  - **Password**: `password`

## Additional Information

- Swagger UI is available at `http://localhost:8000/swagger-ui/`.
