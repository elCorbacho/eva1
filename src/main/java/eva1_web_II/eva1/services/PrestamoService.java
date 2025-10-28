package eva1_web_II.eva1.services;

import eva1_web_II.eva1.models.Libro;
import eva1_web_II.eva1.models.Prestamo;
import eva1_web_II.eva1.models.Usuario;
import eva1_web_II.eva1.repositories.PrestamoRepository;
import eva1_web_II.eva1.repositories.LibroRepository;
import eva1_web_II.eva1.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoService {
    
    @Autowired
    private PrestamoRepository prestamoRepository;
    
    @Autowired
    private LibroRepository libroRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public List<Prestamo> obtenerTodos() {
        return prestamoRepository.findAll();
    }
    
    public Prestamo obtenerPorId(Long id) {
        return prestamoRepository.findById(id).orElse(null);
    }
    
    public List<Prestamo> obtenerPorUsuario(Long usuarioId) {
        return prestamoRepository.findByUsuarioId(usuarioId);
    }
    
    public Prestamo realizarPrestamo(Long libroId, Long usuarioId) {
        Libro libro = libroRepository.findById(libroId).orElse(null);
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        
        if (libro == null || usuario == null) {
            throw new RuntimeException("Libro o Usuario no encontrado");
        }
        
        if (libro.getCopiasDisponibles() <= 0) {
            throw new RuntimeException("No hay copias disponibles");
        }
        
        // Reducir copias disponibles
        libro.setCopiasDisponibles(libro.getCopiasDisponibles() - 1);
        libroRepository.save(libro);
        
        // Crear préstamo
        Prestamo prestamo = new Prestamo();
        prestamo.setLibro(libro);
        prestamo.setUsuario(usuario);
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setFechaDevolucionEsperada(LocalDate.now().plusDays(14)); // 14 días
        prestamo.setEstado(Prestamo.EstadoPrestamo.ACTIVO);
        
        return prestamoRepository.save(prestamo);
    }
    
    public void devolverLibro(Long prestamoId) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId).orElse(null);
        
        if (prestamo == null) {
            throw new RuntimeException("Préstamo no encontrado");
        }
        
        // Aumentar copias disponibles
        Libro libro = prestamo.getLibro();
        libro.setCopiasDisponibles(libro.getCopiasDisponibles() + 1);
        libroRepository.save(libro);
        
        // Actualizar préstamo
        prestamo.setFechaDevolucionReal(LocalDate.now());
        prestamo.setEstado(Prestamo.EstadoPrestamo.DEVUELTO);
        prestamoRepository.save(prestamo);
    }
}
