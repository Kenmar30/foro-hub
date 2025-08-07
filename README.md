## ?? Foro Hub

Una API REST construida con Spring Boot para la gestión de **tópicos de discusión**, al estilo de un foro técnico. Diseñada para CRUD completo, validaciones robustas, y preparada para integrarse con autenticación y autorización.

---

### ?? Características implementadas

- ? Registro de tópicos (POST)
- ? Listado de tópicos (GET)
- ? Detalle de un tópico por ID (GET /topicos/{id})
- ? Actualización de tópicos (PUT /topicos/{id})
- ? Eliminación de tópicos (DELETE /topicos/{id})
- ? Validaciones con @Valid
- ? Conexión a base de datos con Spring Data JPA
- ? Entidades: Topico, Autor, Curso
- ? Pruebas realizadas con Postman

---

### ??? Tecnologías utilizadas

- Java 17
- Spring Boot
- Maven
- Spring Web
- Spring Data JPA
- MySQL / H2 (según configuración)
- Lombok
- Postman (para pruebas manuales)
- Git y GitHub

---

### ??? Estructura del proyecto

\\\plaintext
src/
 +-- main/
     +-- java/
     ¦   +-- com.foro.foroHub/
     ¦       +-- controller/
     ¦       +-- dto/
     ¦       +-- model/
     ¦       +-- repository/
     +-- resources/
         +-- application.properties
\\\

---

### ?? Cómo ejecutar el proyecto localmente

1. Clona el repositorio:

\\\ash
git clone https://github.com/Kenmar30/foro-hub.git
\\\

2. Abre el proyecto con tu IDE favorito (IntelliJ IDEA, Eclipse, VSCode)

3. Asegúrate de tener MySQL corriendo (o cambia a H2 si lo deseas)

4. Configura tus credenciales en \pplication.properties\

5. Ejecuta la clase \ForoHubApplication.java\

6. Prueba los endpoints desde Postman:
   - \GET /topicos\
   - \POST /topicos\
   - \PUT /topicos/{id}\
   - \DELETE /topicos/{id}\

---

### ?? Estado del proyecto

?? En desarrollo activo — se planea añadir:

- ? Autenticación con JWT
- ? Paginación y ordenamiento
- ? Filtros por curso y año
- ? Documentación con Swagger

---

### ?? Licencia

Este proyecto está bajo licencia [MIT](https://choosealicense.com/licenses/mit/)
