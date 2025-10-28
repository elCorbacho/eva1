package eva1_web_II.eva1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import eva1_web_II.eva1.services.PrestamoService;

@Controller
@RequestMapping("/prestamos")
public class PrestamoController {
    
    @Autowired
    private PrestamoService prestamoService;
    
    @GetMapping
    public String listarPrestamos(Model model) {
        model.addAttribute("prestamos", prestamoService.obtenerTodos());
        return "prestamos/lista";
    }
    
    @PostMapping("/realizar")
    public String realizarPrestamo(@RequestParam Long libroId, 
                                   @RequestParam Long usuarioId) {
        prestamoService.realizarPrestamo(libroId, usuarioId);
        return "redirect:/prestamos";
    }
    
    @PostMapping("/devolver/{id}")
    public String devolverLibro(@PathVariable Long id) {
        prestamoService.devolverLibro(id);
        return "redirect:/prestamos";
    }
    
    @GetMapping("/usuario/{usuarioId}")
    public String prestamosUsuario(@PathVariable Long usuarioId, Model model) {
        model.addAttribute("prestamos", prestamoService.obtenerPorUsuario(usuarioId));
        return "prestamos/usuario";
    }
}