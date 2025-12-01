# Backend Development Guidelines (Spring Boot)

## Tech Stack
- Java 17
- Spring Boot 3.5.8
- Maven
- PostgreSQL
- Spring Web (REST)
- Spring Data JPA

---

## Project Structure

```
src/main/java/com/opinix/backend/
│
├── controller/      # REST endpoints
├── service/         # Business logic
├── model/           # JPA entities
├── repository/      # DB access (JPA)
├── dto/             # Request/response classes
└── BackendApplication.java

src/main/resources/
└── application.yaml
```

---

## Architectural Rules

### Controllers
- Contain **no logic**
- Only handle:
  - routing  
  - request validation  
  - calling service methods  

### Services
- Handle all processing & business logic
- Call repositories
- Call ML service when needed

### Repositories
- Extend JPA interfaces only
- No custom SQL unless needed

### DTOs
- Always use DTOs for input/output
- Never expose JPA entities directly

---

## ML Service Communication

Template:

```java
HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_JSON);

HttpEntity<AnalysisRequest> req = new HttpEntity<>(request, headers);

ResponseEntity<AnalysisResponse> response = restTemplate.postForEntity(
    "http://localhost:8001/analyze",
    req,
    AnalysisResponse.class
);
```

---

## API Example Pattern

Controller:
```java
@GetMapping("/polls/{id}")
public PollResponse getPoll(@PathVariable Long id) {
    return pollService.getPoll(id);
}
```

Service:
```java
public PollResponse getPoll(Long id) {
    Poll poll = pollRepository.findById(id).orElseThrow();
    return mapper.toResponse(poll);
}
```

---

## AI Usage Rules

When generating backend code:

1. Follow controller → service → repository pattern.
2. Use DTOs for all inputs/outputs.
3. Don’t generate extra dependencies.
4. Use Java 17 & Spring Boot 3 conventions.
5. Only produce small patches unless told otherwise.
6. Respect the project structure exactly as listed.

---
