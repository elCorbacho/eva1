package eva1_web_II.eva1.services;

import eva1_web_II.eva1.models.Usuario;
import eva1_web_II.eva1.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }
    
    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }
    
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
    
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }
    
    public Usuario buscarPorRut(String rut) {
        return usuarioRepository.findByRut(rut).orElse(null);
    }
}
