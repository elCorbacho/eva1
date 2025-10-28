# 📚 Sistema de Gestión de Biblioteca - Spring Boot

> **Para desarrolladores que vienen de Laravel/PHP**

## 🎯 ¿Qué es este proyecto?

Es un sistema de información para una biblioteca que permite:
- ✅ Buscar libros por título o autor
- ✅ Consultar disponibilidad de libros
- ✅ Realizar préstamos y devoluciones
- ✅ Gestionar usuarios
- ✅ Ver historial de préstamos

---

## 🔧 Requisitos Previos

1. **Java 21** (equivalente a PHP 8.x)
2. **Maven** (equivalente a Composer)
3. **IDE**: IntelliJ IDEA, Eclipse o VS Code

### Verificar instalación:
```bash
java -version    # Debe mostrar Java 21
./mvnw -v        # Maven wrapper incluido en el proyecto
```

---

## 🚀 Cómo Ejecutar el Proyecto

### 1️⃣ Clonar o abrir el proyecto
```bash
cd c:\Users\histo\OneDrive\Escritorio\eva1\eva1
```

### 2️⃣ Instalar dependencias (como `composer install`)
```bash
./mvnw clean install
```

### 3️⃣ Ejecutar la aplicación (como `php artisan serve`)
```bash
./mvnw spring-boot:run
```

### 4️⃣ Abrir en el navegador
```
http://localhost:8080
```

---

## 📂 Estructura del Proyecto (vs Laravel)

| Spring Boot | Laravel | Descripción |
|-------------|---------|-------------|
| `src/main/java/controllers/` | `app/Http/Controllers/` | Controladores |
| `src/main/java/models/` | `app/Models/` | Modelos (Entities) |
| `src/main/java/services/` | `app/Services/` | Lógica de negocio |
| `src/main/java/repositories/` | *Eloquent integrado* | Acceso a datos |
| `src/main/resources/templates/` | `resources/views/` | Vistas HTML |
| `src/main/resources/application.properties` | `.env` | Configuración |
| `src/main/resources/data.sql` | `database/seeders/` | Datos iniciales |
| `pom.xml` | `composer.json` | Dependencias |

---

## 🏗️ Arquitectura (Comparación con Laravel)

### **En Laravel:**
```
Request → Route → Controller → Model (Eloquent) → View
```

### **En Spring Boot:**
```
Request → Controller → Service → Repository → Entity → Database
                ↓
              View
```

### ¿Por qué más capas?

| Capa | Laravel | Spring Boot | Razón |
|------|---------|-------------|-------|
| **Controller** | Maneja todo | Solo HTTP | Separación de responsabilidades |
| **Service** | Opcional | **Obligatorio** | Lógica de negocio reutilizable |
| **Repository** | Eloquent lo hace | **Manual** | Control total sobre consultas |

**Ventaja**: Mejor para proyectos grandes y testing.

---

## 📋 Modelos (Entities)

### Comparación Laravel vs Spring Boot

#### **Laravel (Eloquent)**
```php
class Libro extends Model {
    protected $fillable = ['titulo', 'autor'];
    
    // Métodos ya incluidos:
    // Libro::all(), find($id), where()->get()
}
```

#### **Spring Boot (JPA)**
```java
@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String titulo;
    private String autor;
    
    // Getters y Setters obligatorios
}
```

**Diferencia clave**: En Laravel, Eloquent hace todo automáticamente. En Spring Boot, necesitas definir todo explícitamente.

---

## 🗄️ Repositories (vs Eloquent)

### **Laravel**
```php
// NO necesitas crear nada, Eloquent lo hace:
$libros = Libro::all();
$libro = Libro::find(1);
```

### **Spring Boot**
```java
// Debes crear una interfaz Repository:
@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByTituloContaining(String titulo);
    // Spring genera automáticamente el SQL
}
```

**Ventaja**: Métodos como `findByTituloContaining` se generan automáticamente por convención.

---

## 🔧 Services (Lógica de Negocio)

### **Laravel (Opcional)**
```php
// Puedes hacerlo directo en el Controller:
class LibroController {
    public function index() {
        return Libro::all();
    }
}
```

### **Spring Boot (Obligatorio)**
```java
// Debes crear un Service:
@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;
    
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }
}
```

**Razón**: Separa la lógica de negocio del controlador (mejor para testing).

---

## 🎮 Controladores

### **Web Controller (HTML)**
```java
@Controller
@RequestMapping("/libros")
public class LibroController {
    @Autowired
    private LibroService libroService;
    
    @GetMapping
    public String listarLibros(Model model) {
        model.addAttribute("libros", libroService.obtenerTodos());
        return "libros/lista"; // Retorna vista HTML
    }
}
```

### **REST Controller (JSON)**
```java
@RestController
@RequestMapping("/api/libros")
public class LibroRestController {
    @Autowired
    private LibroService libroService;
    
    @GetMapping
    public ResponseEntity<List<Libro>> listarLibros() {
        return ResponseEntity.ok(libroService.obtenerTodos());
        // Retorna JSON automáticamente
    }
}
```

---

## 🌐 Rutas Disponibles

### **Rutas Web (HTML en navegador)**
```
http://localhost:8080/                      → Página de inicio
http://localhost:8080/libros                → Lista de libros
http://localhost:8080/libros/1              → Detalle del libro
http://localhost:8080/libros/disponibilidad/1 → Consultar disponibilidad
http://localhost:8080/usuarios              → Lista de usuarios
http://localhost:8080/usuarios/1            → Perfil de usuario
http://localhost:8080/prestamos             → Lista de préstamos
http://localhost:8080/prestamos/usuario/1   → Préstamos de un usuario
```

### **Rutas API REST (JSON para Postman)**

#### 📚 **Libros**
```
GET    /api/libros                    → Listar todos
GET    /api/libros/1                  → Ver uno
GET    /api/libros/buscar?termino=... → Buscar
POST   /api/libros                    → Crear
PUT    /api/libros/1                  → Actualizar
DELETE /api/libros/1                  → Eliminar
```

#### 👥 **Usuarios**
```
GET    /api/usuarios                  → Listar todos
GET    /api/usuarios/1                → Ver uno
GET    /api/usuarios/email/{email}    → Buscar por email
GET    /api/usuarios/rut/{rut}        → Buscar por RUT
POST   /api/usuarios                  → Crear
PUT    /api/usuarios/1                → Actualizar
DELETE /api/usuarios/1                → Eliminar
```

#### 📖 **Préstamos**
```
GET    /api/prestamos                 → Listar todos
GET    /api/prestamos/1               → Ver uno
GET    /api/prestamos/usuario/1       → De un usuario
POST   /api/prestamos/realizar        → Realizar préstamo
PUT    /api/prestamos/devolver/1      → Devolver libro
```

---

## 🧪 Ejemplos con Postman

### 1. Listar todos los libros
```
GET http://localhost:8080/api/libros
```

**Respuesta:**
```json
[
  {
    "id": 1,
    "titulo": "Cien años de soledad",
    "autor": "Gabriel García Márquez",
    "isbn": "978-0307474728",
    "editorial": "Editorial Sudamericana",
    "fechaPublicacion": "1967-06-05",
    "categoria": "Ficción",
    "copiasTotales": 5,
    "copiasDisponibles": 3
  }
]
```

### 2. Crear un libro
```
POST http://localhost:8080/api/libros
Content-Type: application/json

{
  "titulo": "El Aleph",
  "autor": "Jorge Luis Borges",
  "isbn": "978-0142437889",
  "editorial": "Emecé",
  "fechaPublicacion": "1949-06-01",
  "categoria": "Cuentos",
  "copiasTotales": 5,
  "copiasDisponibles": 5
}
```

### 3. Realizar un préstamo
```
POST http://localhost:8080/api/prestamos/realizar
Content-Type: application/json

{
  "libroId": 5,
  "usuarioId": 2
}
```

### 4. Devolver un libro
```
PUT http://localhost:8080/api/prestamos/devolver/1
```

---

## 🗃️ Base de Datos

### **Configuración (application.properties)**
```properties
# H2 Database (en memoria, como SQLite)
spring.datasource.url=jdbc:h2:mem:biblioteca
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# JPA/Hibernate (ORM como Eloquent)
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
```

### **Acceder a la consola H2 (como phpMyAdmin)**
```
http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:biblioteca
User: sa
Password: (dejar vacío)
```

### **Datos de prueba (data.sql)**
Se cargan automáticamente al iniciar la aplicación (como seeders en Laravel).

---

## 🔄 Comparación de Operaciones

| Operación | Laravel | Spring Boot |
|-----------|---------|-------------|
| Listar todos | `Libro::all()` | `libroRepository.findAll()` |
| Buscar por ID | `Libro::find(1)` | `libroRepository.findById(1)` |
| Crear | `Libro::create([...])` | `libroRepository.save(libro)` |
| Actualizar | `$libro->save()` | `libroRepository.save(libro)` |
| Eliminar | `$libro->delete()` | `libroRepository.deleteById(1)` |
| Buscar con filtro | `Libro::where('titulo', 'like', '%...')` | `findByTituloContaining(...)` |

---

## 🎨 Vistas (Thymeleaf vs Blade)

### **Laravel (Blade)**
```blade
@foreach($libros as $libro)
    <h2>{{ $libro->titulo }}</h2>
@endforeach
```

### **Spring Boot (Thymeleaf)**
```html
<div th:each="libro : ${libros}">
    <h2 th:text="${libro.titulo}"></h2>
</div>
```

**Similar pero con sintaxis diferente.**

---

## 🛠️ Comandos Útiles

### **Compilar el proyecto**
```bash
./mvnw clean install
```

### **Ejecutar la aplicación**
```bash
./mvnw spring-boot:run
```

### **Detener la aplicación**
```
Ctrl + C
```

### **Ver logs en tiempo real**
Los logs aparecen en la consola donde ejecutaste `spring-boot:run`

---

## 📦 Dependencias Importantes (pom.xml)

```xml
<!-- Spring Boot Web (como Laravel routing) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- Thymeleaf (como Blade) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

<!-- JPA/Hibernate (como Eloquent) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- H2 Database (como SQLite) -->
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
</dependency>
```

---

## 🔍 Diferencias Clave con Laravel

| Aspecto | Laravel | Spring Boot |
|---------|---------|-------------|
| **Lenguaje** | PHP | Java |
| **ORM** | Eloquent (automático) | JPA/Hibernate (manual) |
| **Rutas** | `routes/web.php` | Anotaciones `@GetMapping` |
| **Inyección** | Automática | `@Autowired` |
| **Migraciones** | `php artisan migrate` | JPA genera tablas automáticamente |
| **Seeders** | `php artisan db:seed` | `data.sql` |
| **Servidor** | `php artisan serve` | `./mvnw spring-boot:run` |
| **Puerto** | 8000 | 8080 |

---

## ❓ Preguntas Frecuentes

### **¿Por qué tantas capas?**
En proyectos grandes, separar Controller → Service → Repository facilita el mantenimiento y testing.

### **¿Es más difícil que Laravel?**
Más verboso, pero más estructurado para aplicaciones empresariales.

### **¿Dónde están las rutas?**
No hay archivo `routes/web.php`. Las rutas se definen con anotaciones en los controladores:
```java
@GetMapping("/libros")  // Equivale a Route::get('/libros', ...)
```

### **¿Cómo reiniciar la BD?**
Al usar `create-drop`, la base de datos se reinicia cada vez que ejecutas la aplicación.

---

## 📞 Soporte

- **Documentación Spring Boot**: https://spring.io/guides
- **Tutorial JPA**: https://spring.io/guides/gs/accessing-data-jpa/
- **Thymeleaf**: https://www.thymeleaf.org/documentation.html

---

## ✅ Checklist Inicial

- [ ] Java 21 instalado
- [ ] Proyecto clonado/abierto
- [ ] Ejecutar `./mvnw clean install`
- [ ] Ejecutar `./mvnw spring-boot:run`
- [ ] Abrir http://localhost:8080
- [ ] Probar rutas con Postman
- [ ] Acceder a H2 Console

---

**🎉 ¡Listo! Ya tienes tu sistema de biblioteca funcionando.**
