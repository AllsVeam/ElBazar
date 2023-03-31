package AE_Desing.ElBazar.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import AE_Desing.ElBazar.entity.Editorial;

public interface IntServiceEditorial {
		
	public List<Editorial> obtenerEditorial();
	public void guardar (Editorial editorial);
	public Editorial buscarPorId (Integer idEditorial);
	public void eliminar (Integer idEditorial);
	public int numeroEditorial();
	Page<Editorial>buscarTodas(Pageable page);
}
