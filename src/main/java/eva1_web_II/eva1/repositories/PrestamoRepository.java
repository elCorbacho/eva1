package eva1_web_II.eva1.repositories;

import eva1_web_II.eva1.models.Prestamo;
//import eva1_web_II.eva1.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findByUsuarioId(Long usuarioId);
    List<Prestamo> findByEstado(Prestamo.EstadoPrestamo estado);
}
