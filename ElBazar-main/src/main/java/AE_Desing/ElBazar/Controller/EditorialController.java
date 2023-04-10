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

import AE_Desing.ElBazar.entity.Editorial;
import AE_Desing.ElBazar.entity.Libro;
import AE_Desing.ElBazar.services.IntServiceEditorial;
import AE_Desing.ElBazar.services.IntServiceLibros;

@RequestMapping("/editorial")
@Controller
public class EditorialController {

	@Autowired
	private IntServiceEditorial serviceEdit;
	
	@Autowired
	private IntServiceLibros serviceLib;

	@GetMapping("/index")
	public String mostrarIndex(Model model, Pageable page) {
		Page<Editorial> editorial = serviceEdit.buscarTodas(page);
		model.addAttribute("editorial", editorial);
		model.addAttribute("total", serviceEdit.obtenerEditorial().size());
		return "editorial/listaEditorial";
	}

	@GetMapping("/buscar")
	public String modificar(@RequestParam("id") int idEditorial, Model model) {
		Editorial editorial = serviceEdit.buscarPorId(idEditorial);
		model.addAttribute("editorial", editorial);
		return "editorial/formEditorial";
	}

	@GetMapping("/eliminar")
	public String eliminar(@RequestParam("id") int idEditorial, RedirectAttributes model) {		
		for (Libro libro : serviceLib.obtenerLibros()) {
			if (libro.getEditorial().getId() == idEditorial) {				
				model.addFlashAttribute("msg", "La Editorial no se puede eliminar pertenece a un libro");				
			}else {
				model.addFlashAttribute("msg", "Editorial Eliminada correctamente");
				serviceEdit.eliminar(idEditorial);
			}
		}			
		return "redirect:/editorial/index";
	}

	@GetMapping("/nueva")
	public String nueva(Editorial editorial) {
		return "editorial/formEditorial";
	}

	@PostMapping("/guardar")
	public String guardar(Editorial editorial) {
		serviceEdit.guardar(editorial);
		return "redirect:/editorial/index";
	}

}
