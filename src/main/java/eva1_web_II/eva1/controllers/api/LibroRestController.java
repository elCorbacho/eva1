package eva1_web_II.eva1.controllers.api;

import eva1_web_II.eva1.models.Libro;
import eva1_web_II.eva1.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroRestController {
    
    @Autowired
    private LibroService libroService;
    
    // GET /api/libros - Listar todos los libros
    @GetMapping
    public ResponseEntity<List<Libro>> listarLibros() {
        List<Libro> libros = libroService.obtenerTodos();
        return ResponseEntity.ok(libros);
    }
    
    // GET /api/libros/{id} - Obtener un libro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibro(@PathVariable Long id) {
        Libro libro = libroService.obtenerPorId(id);
        if (libro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(libro);
    }
    
    // GET /api/libros/buscar?termino=... - Buscar libros
    @GetMapping("/buscar")
    public ResponseEntity<List<Libro>> buscarLibros(@RequestParam String termino) {
        List<Libro> libros = libroService.buscar(termino);
        return ResponseEntity.ok(libros);
    }
    
    // POST /api/libros - Crear un nuevo libro
    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        Libro nuevoLibro = libroService.guardar(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLibro);
    }
    
    // PUT /api/libros/{id} - Actualizar un libro
    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        Libro libroExistente = libroService.obtenerPorId(id);
        if (libroExistente == null) {
            return ResponseEntity.notFound().build();
        }
        libro.setId(id);
        Libro libroActualizado = libroService.guardar(libro);
        return ResponseEntity.ok(libroActualizado);
    }
    
    // DELETE /api/libros/{id} - Eliminar un libro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        Libro libro = libroService.obtenerPorId(id);
        if (libro == null) {
            return ResponseEntity.notFound().build();
        }
        libroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
