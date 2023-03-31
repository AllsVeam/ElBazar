package AE_Desing.ElBazar.Controller;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import AE_Desing.ElBazar.entity.Libro;
import AE_Desing.ElBazar.services.IntServiceAutor;
import AE_Desing.ElBazar.services.IntServiceClasificacion;
import AE_Desing.ElBazar.services.IntServiceEditorial;
import AE_Desing.ElBazar.services.IntServiceLibros;
import AE_Desing.ElBazar.util.Utileria;

@Controller
@RequestMapping("/libro")
public class LibroController {
	
	@Value("${proyecto.ruta.imagenes}")
	private String ruta;
	
	@Autowired
	private IntServiceLibros serviceLib;
	
	@Autowired
	private IntServiceEditorial serviceEdit;
	
	@Autowired
	private IntServiceAutor serviceAutor;
	
	@Autowired
	private IntServiceClasificacion serviceClas;
	
	@Autowired
	private Utileria util;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model, Pageable page){
		Page<Libro> libro = serviceLib.buscarTodas(page);
		model.addAttribute("libro", libro);
		model.addAttribute("total", serviceLib.obtenerLibros().size());		
		return "libro/listaLibros";
	}
	
	@GetMapping("/nuevo")
	public String agregarLibro(Libro libro, Model model){
		model.addAttribute("clasificacion", serviceClas.obtenerClasificaciones());
		model.addAttribute("autor", serviceAutor.obtenerAutor());
		model.addAttribute("editorial", serviceEdit.obtenerEditorial());
		return "libro/formLibro";
	}
	
	@PostMapping("/guardar")
	public String guardarLibro(Libro libro, BindingResult result, @RequestParam("archivoImagen") MultipartFile file, RedirectAttributes model) {
		if(result.hasErrors()) {
			for(ObjectError error: result.getAllErrors()) {
				System.out.println("Ocurrio un error: "+error.getDefaultMessage());
			}
			model.addAttribute("libro", serviceLib.obtenerLibros());
			return "libro/formLibro";
		}
		if (libro.getId() == null) {
			if (!file.isEmpty()) {
				String fileName = util.uploadImage(file);
				if (fileName != null) {
					libro.setPortada(fileName);
				}
			}
			model.addFlashAttribute("msg", "La información del producto ha sido agregada correctamente.");
		} else {
			if (!file.isEmpty()) {
				String fileName = util.uploadImage(file);
				if (fileName != null) {
					libro.setPortada(fileName);
					model.addFlashAttribute("msg", "La información del producto ha sido modificada correctamente.");
				}
			} else {
				Libro p = serviceLib.buscarPorId(libro.getId());
				libro.setPortada(p.getPortada());
			}
		}

		serviceLib.guardar(libro);
		return "redirect:/libro/index";
	}
	
	@GetMapping("/buscar")
	public String buscarLibro(@RequestParam("id") int idLibro, Model model){
		model.addAttribute("clasificacion", serviceClas.obtenerClasificaciones());
		model.addAttribute("autor", serviceAutor.obtenerAutor());
		model.addAttribute("editorial", serviceClas.obtenerClasificaciones());
		model.addAttribute("libro", serviceLib.buscarPorId(idLibro));
		return "libro/formLibro";
	}
	
	@GetMapping("/eliminar")
	public String eliminarLibro(@RequestParam("id") int idLibro, RedirectAttributes model){		
		serviceLib.eliminar(idLibro);
		model.addFlashAttribute("msg", "Libro eliminado de forma correcta");
		return "redirect:/libro/index";
	}
	
	@GetMapping("/detalle")
	public String detalleLibro(@RequestParam("id") int idLibro, Model model){		
		model.addAttribute("libro", serviceLib.buscarPorId(idLibro));
		return "libro/detalle";
	}
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
        @Override
        public void setAsText(String text) throws IllegalArgumentException{
          setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }

        @Override
        public String getAsText() throws IllegalArgumentException {
          return DateTimeFormatter.ofPattern("dd-MM-yyyy").format((LocalDate) getValue());
        }  
    
      });
	}
}
