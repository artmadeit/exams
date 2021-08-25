# Exams

- Instalar vscode java
- Instalar https://sdkman.io/
- Instalar java 16 con sdkman: `sdk install java 16.0.1.hs-adpt`
- Crear BD, conectar a psql y luego: `create database exams;`
- Levantar app: `./mvnw spring-boot:run`



## Test
### Unit testing
- `mvn test`
- `mvn jacoco:report` for test coverage

### Load testing
- Run test workflow folder in postman
- Install [k6](https://k6.io/docs/getting-started/installation/)
- Run test stress script with k6: `k6 run stress.js`


TODO:
- ejecutar prueba de performance (al iniciar un examen y actualizar una pregunta y respuesta)