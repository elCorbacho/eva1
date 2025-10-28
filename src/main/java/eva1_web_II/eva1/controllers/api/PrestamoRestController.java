package eva1_web_II.eva1.controllers.api;

import eva1_web_II.eva1.models.Prestamo;
import eva1_web_II.eva1.services.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoRestController {
    
    @Autowired
    private PrestamoService prestamoService;
    
    // GET /api/prestamos - Listar todos los préstamos
    @GetMapping
    public ResponseEntity<List<Prestamo>> listarPrestamos() {
        List<Prestamo> prestamos = prestamoService.obtenerTodos();
        return ResponseEntity.ok(prestamos);
    }
    
    // GET /api/prestamos/{id} - Obtener un préstamo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> obtenerPrestamo(@PathVariable Long id) {
        Prestamo prestamo = prestamoService.obtenerPorId(id);
        if (prestamo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(prestamo);
    }
    
    // GET /api/prestamos/usuario/{usuarioId} - Préstamos de un usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Prestamo>> prestamosUsuario(@PathVariable Long usuarioId) {
        List<Prestamo> prestamos = prestamoService.obtenerPorUsuario(usuarioId);
        return ResponseEntity.ok(prestamos);
    }
    
    // POST /api/prestamos/realizar - Realizar un préstamo
    @PostMapping("/realizar")
    public ResponseEntity<?> realizarPrestamo(@RequestBody Map<String, Long> request) {
        try {
            Long libroId = request.get("libroId");
            Long usuarioId = request.get("usuarioId");
            
            Prestamo prestamo = prestamoService.realizarPrestamo(libroId, usuarioId);
            return ResponseEntity.status(HttpStatus.CREATED).body(prestamo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    // PUT /api/prestamos/devolver/{id} - Devolver un libro
    @PutMapping("/devolver/{id}")
    public ResponseEntity<?> devolverLibro(@PathVariable Long id) {
        try {
            prestamoService.devolverLibro(id);
            return ResponseEntity.ok().body(Map.of("message", "Libro devuelto exitosamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
