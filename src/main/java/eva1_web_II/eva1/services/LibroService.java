package eva1_web_II.eva1.services;

import eva1_web_II.eva1.models.Libro;
import eva1_web_II.eva1.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
//import java.util.Optional;

@Service
public class LibroService {
    
    @Autowired
    private LibroRepository libroRepository;
    
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }
    
    public Libro obtenerPorId(Long id) {
        return libroRepository.findById(id).orElse(null);
    }
    
    public List<Libro> buscar(String termino) {
        return libroRepository.findByTituloContainingIgnoreCaseOrAutorContainingIgnoreCase(termino, termino);
    }
    
    public Libro guardar(Libro libro) {
        return libroRepository.save(libro);
    }
    
    public void eliminar(Long id) {
        libroRepository.deleteById(id);
    }
}