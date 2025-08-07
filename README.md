## ?? Foro Hub

Una API REST construida con Spring Boot para la gesti�n de **t�picos de discusi�n**, al estilo de un foro t�cnico. Dise�ada para CRUD completo, validaciones robustas, y preparada para integrarse con autenticaci�n y autorizaci�n.

---

### ?? Caracter�sticas implementadas

- ? Registro de t�picos (POST)
- ? Listado de t�picos (GET)
- ? Detalle de un t�pico por ID (GET /topicos/{id})
- ? Actualizaci�n de t�picos (PUT /topicos/{id})
- ? Eliminaci�n de t�picos (DELETE /topicos/{id})
- ? Validaciones con @Valid
- ? Conexi�n a base de datos con Spring Data JPA
- ? Entidades: Topico, Autor, Curso
- ? Pruebas realizadas con Postman

---

### ??? Tecnolog�as utilizadas

- Java 17
- Spring Boot
- Maven
- Spring Web
- Spring Data JPA
- MySQL / H2 (seg�n configuraci�n)
- Lombok
- Postman (para pruebas manuales)
- Git y GitHub

---

### ??? Estructura del proyecto

\\\plaintext
src/
 +-- main/
     +-- java/
     �   +-- com.foro.foroHub/
     �       +-- controller/
     �       +-- dto/
     �       +-- model/
     �       +-- repository/
     +-- resources/
         +-- application.properties
\\\

---

### ?? C�mo ejecutar el proyecto localmente

1. Clona el repositorio:

\\\ash
git clone https://github.com/Kenmar30/foro-hub.git
\\\

2. Abre el proyecto con tu IDE favorito (IntelliJ IDEA, Eclipse, VSCode)

3. Aseg�rate de tener MySQL corriendo (o cambia a H2 si lo deseas)

4. Configura tus credenciales en \pplication.properties\

5. Ejecuta la clase \ForoHubApplication.java\

6. Prueba los endpoints desde Postman:
   - \GET /topicos\
   - \POST /topicos\
   - \PUT /topicos/{id}\
   - \DELETE /topicos/{id}\

---

### ?? Estado del proyecto

?? En desarrollo activo � se planea a�adir:

- ? Autenticaci�n con JWT
- ? Paginaci�n y ordenamiento
- ? Filtros por curso y a�o
- ? Documentaci�n con Swagger

---

### ?? Licencia

Este proyecto est� bajo licencia [MIT](https://choosealicense.com/licenses/mit/)
