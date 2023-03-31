package AE_Desing.ElBazar.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/empleados")
@Controller
public class EmpleadoController {

	@GetMapping("/index")
	public String mostrarIndex(){		
		return "/empleados/listaEmpleados";
	}
}
