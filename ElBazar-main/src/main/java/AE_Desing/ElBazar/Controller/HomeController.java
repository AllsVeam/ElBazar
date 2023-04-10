package AE_Desing.ElBazar.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import AE_Desing.ElBazar.entity.Perfil;
import AE_Desing.ElBazar.entity.Usuario;
import AE_Desing.ElBazar.services.IntServiceClasificacion;
import AE_Desing.ElBazar.services.IntServiceLibros;
import AE_Desing.ElBazar.services.IntServiceUsuario;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	
	@Autowired
	private IntServiceLibros serviceLib;
	
	@Autowired
	private IntServiceUsuario serviceUsuario;
	
	@Autowired
	private IntServiceClasificacion serviceClas;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/")
	public String mostrarIndex(Model model){
		model.addAttribute("relevante", serviceLib.obtenerRelevantes());				
		return "index";
	}
	
	@GetMapping("/libro")
	public String mostrarLibro(@RequestParam("id") int idCat, Model model){
		if(idCat==0) {
			model.addAttribute("clasificacion", serviceClas.obtenerClasificaciones());
			model.addAttribute("libro", serviceLib.obtenerLibros());
			return "libro";
		}else{
			model.addAttribute("clasificacion", serviceClas.obtenerClasificaciones());
			model.addAttribute("libro", serviceLib.buscarPorClas(idCat));
			return "libro";
		}
	}
	
	@GetMapping("/clasificacion")
	public String mostrarClasificacion(){		
		return "clasificacion";
	}
	
	@GetMapping("/acerca")
	public String mostrarAcerca(){		
		return "acerca";
	}
	
	@GetMapping("/login")
	public String login() {		
		return "login";
	}
	
	@GetMapping("/error/**")
	public String error() {		
		return "redirect:/";
	}
	
	@GetMapping("/error")
	public String error1() {		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/";
	}
	
	@GetMapping("/registro")
	public String registro(){		
		return "registrarse";
	}
	
	@PostMapping("/guardar")
	public String guardar(Usuario usuario) {
		usuario.setEstatus(1);		
		// Encryptar usuario
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		Perfil per = new Perfil();
		per.setId(3);
		usuario.agregar(per);
		serviceUsuario.guardar(usuario);
		System.out.println(usuario);
		return "redirect:/";
	}
}