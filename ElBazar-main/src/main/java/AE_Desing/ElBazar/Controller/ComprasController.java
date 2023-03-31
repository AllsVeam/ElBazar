package AE_Desing.ElBazar.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/compras")
@Controller
public class ComprasController {
	
	@GetMapping("/index")
	public String mostrarIndex(){		
		return "/compras/listaCompras";
	}
}
