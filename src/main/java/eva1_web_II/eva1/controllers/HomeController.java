package eva1_web_II.eva1.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class HomeController {

	@GetMapping("/")
	public String index(Model model) {
        model.addAttribute("mensaje", "¡Bienvenido a la página de inicio!");    
        return "index";
	}

}
