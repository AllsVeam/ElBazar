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

import AE_Desing.ElBazar.entity.Clasificacion;
import AE_Desing.ElBazar.entity.Libro;
import AE_Desing.ElBazar.services.IntServiceClasificacion;
import AE_Desing.ElBazar.services.IntServiceLibros;

@RequestMapping("/clasificacion")
@Controller
public class ClasificacionController {
	
	@Autowired 
	private IntServiceClasificacion serviceClas;
	
	@Autowired
	private IntServiceLibros serviceLib;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model, Pageable page){	
		Page<Clasificacion>clasificacion = serviceClas.buscarTodas(page);		
		model.addAttribute("clasificacion", clasificacion);
		model.addAttribute("total", serviceClas.obtenerClasificaciones().size());
		return "clasificacion/listaClasificacion";
	}
	
	@GetMapping("/buscar")
	public String modificar(@RequestParam("id") int idClasificacion ,Model model){	
		Clasificacion clasificacion = serviceClas.buscarPorId(idClasificacion);
		model.addAttribute("clasificacion", clasificacion);
		return "clasificacion/formClasificacion";
	}
	
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam("id") int idClasificacion ,RedirectAttributes model){
		for (Libro libro : serviceLib.obtenerLibros()) {
			if (libro.getEditorial().getId()==idClasificacion) {				
				model.addFlashAttribute("msg", "La Clasificacion no se puede eliminar pertenece a un libro");
				return "redirect:/clasificacion/index";
			}
		}
		serviceClas.eliminar(idClasificacion);
		model.addFlashAttribute("msg", "Clasificacion Eliminada correctamente");
		return "redirect:/clasificacion/index";
	}
	
	@GetMapping("/nueva")
	public String nueva(Clasificacion clasificacion) {
		return "clasificacion/formClasificacion";
	}
	
	@PostMapping("/guardar")
	public String guardar(Clasificacion clasificacion) {
		serviceClas.guardar(clasificacion);
		return "redirect:/clasificacion/index";
	}
	
}
