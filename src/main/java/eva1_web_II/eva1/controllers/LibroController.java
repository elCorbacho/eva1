package eva1_web_II.eva1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import eva1_web_II.eva1.services.LibroService;

@Controller
@RequestMapping("/libros")
public class LibroController {
    
    @Autowired
    private LibroService libroService;
    
    @GetMapping
    public String listarLibros(Model model) {
        model.addAttribute("libros", libroService.obtenerTodos());
        return "libros/lista";
    }
    
    @GetMapping("/buscar")
    public String buscarLibros(@RequestParam String termino, Model model) {
        model.addAttribute("libros", libroService.buscar(termino));
        return "libros/lista";
    }
    
    @GetMapping("/{id}")
    public String verDetalle(@PathVariable Long id, Model model) {
        model.addAttribute("libro", libroService.obtenerPorId(id));
        return "libros/detalle";
    }
    
    @GetMapping("/disponibilidad/{id}")
    public String consultarDisponibilidad(@PathVariable Long id, Model model) {
        model.addAttribute("libro", libroService.obtenerPorId(id));
        return "libros/disponibilidad";
    }
}
