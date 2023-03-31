package AE_Desing.ElBazar.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import AE_Desing.ElBazar.entity.Autor;
import AE_Desing.ElBazar.services.IntServiceAutor;

@RequestMapping("/autor")
@Controller
public class AutorController {
	
	@Autowired IntServiceAutor serviceAutor;

	@GetMapping("/index")
	public String mostrarIndex(Model model, Pageable page){	
		Page<Autor> autor= serviceAutor.buscarTodas(page);		
		model.addAttribute("autor", autor);
		model.addAttribute("total", serviceAutor.obtenerAutor().size());
		return "autor/listaAutor";
	}
	
	@GetMapping("/buscar")
	public String modificar(@RequestParam("id") int idAutor,Model model){	
		Autor autor= serviceAutor.buscarPorId(idAutor);
		model.addAttribute("autor", autor);
		return "autor/formAutor";
	}
	
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam("id") int idAutor,RedirectAttributes model){	
		serviceAutor.eliminar(idAutor);
		model.addFlashAttribute("msg", "Autor Eliminado correctamente");
		return "redirect:/autor/index";
	}
	
	@GetMapping("/nueva")
	public String nueva(Autor autor) {
		return "autor/formAutor";
	}
	
	@PostMapping("/guardar")
	public String guardar(Autor autor) {
		serviceAutor.guardar(autor);
		return "redirect:/autor/index";
	}
}
