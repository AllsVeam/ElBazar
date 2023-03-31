package AE_Desing.ElBazar.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import AE_Desing.ElBazar.entity.Libro;

public interface IntServiceLibros {
	public List<Libro> obtenerLibros();
	public List<Libro> obtenerRelevantes();
	public void guardar (Libro libro);
	public Libro buscarPorId (Integer idLibro);
	public List<Libro> buscarPorClas (Integer idLibro);
	public void eliminar (Integer idLibro);
	public long numeroLibros();
	Page<Libro>buscarTodas(Pageable page);

}
