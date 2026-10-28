# Progress

## Current phase
**Phase 1: Backend skeleton**

**Next up:** Step 2 — Docker Compose Postgres + local config (fixes the DataSource startup failure).

## Decisions
- Monorepo: backend lives in `hoard-api/` inside this repo
- Maven (not Gradle) for the backend
- Spring Boot 4.1.2 (not 3 — Spring Boot 3.x is past end of free support and no longer on Initializr)
- Java 21
- Base package: `com.hoard.api`

## Phase 1 steps
- [x] 1. Generate `hoard-api` project (Web, Data JPA, Validation, Flyway, PostgreSQL, Testcontainers)
- [ ] 2. Docker Compose Postgres + `application-local` config from env vars, `ddl-auto: validate`
- [ ] 3. `V1__init.sql` — `item` table
- [ ] 4. `Item` JPA entity
- [ ] 5. `ItemRepository`
- [ ] 6. `ItemRequest` / `ItemResponse` records with validation
- [ ] 7. `ItemService`
- [ ] 8. `ItemController`
- [ ] 9. Global exception handler (`ProblemDetail`)
- [ ] 10. Testcontainers integration test (pin Postgres image version — generated config uses `postgres:latest`)

## Concepts learned
- **Flyway vs Hibernate:** Flyway owns the schema via versioned SQL migrations; Hibernate only validates. Flyway stores checksums in `flyway_schema_history`, so editing an already-run migration fails startup.
- **Maven wrapper (`./mvnw`):** pins the Maven version per project; no global install needed.
- **Auto-configuration:** Spring Boot creates beans based on the classpath. JPA + Postgres driver on the classpath → it tries to build a `DataSource` and fails without a URL.
- **Boot 4 starters:** `spring-boot-starter-webmvc` (was `-web`), separate `spring-boot-starter-flyway`, per-feature `*-test` starters.
- **Testcontainers files:** `TestcontainersConfiguration` and `TestHoardApiApplication` come from the Testcontainers dependency (not JUnit). `@ServiceConnection` wires the container's connection details into the `DataSource` automatically.
