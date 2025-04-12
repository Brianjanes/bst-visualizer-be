# BST Visualizer

A Binary Search Tree visualization tool with Spring Boot backend and React frontend.

## Backend (Port 8080)

### Requirements

- Java 17+
- MongoDB running on port 27017

### Running the Backend

```bash
./mvnw spring-boot:run
```

### API Endpoints

- `POST /api/process-numbers` - Creates a new BST from an array of numbers
- `GET /api/previous-trees` - Retrieves all previously created trees

## Frontend (Port 3000)

The frontend is a separate React application that communicates with the backend API.

### Running the Frontend

```bash
cd frontend
npm install
npm start
```
