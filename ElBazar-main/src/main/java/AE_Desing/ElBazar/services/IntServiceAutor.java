package AE_Desing.ElBazar.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import AE_Desing.ElBazar.entity.Autor;

public interface IntServiceAutor {
	
	public List<Autor> obtenerAutor();
	public void guardar (Autor autor);
	public Autor buscarPorId (Integer idAutor);
	public void eliminar (Integer idAutor);
	public int numeroAutor();
	Page<Autor>buscarTodas(Pageable page);

}
