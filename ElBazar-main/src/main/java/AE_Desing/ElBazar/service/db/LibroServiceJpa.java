package AE_Desing.ElBazar.service.db;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import AE_Desing.ElBazar.entity.Libro;
import AE_Desing.ElBazar.repository.LibrosRepository;
import AE_Desing.ElBazar.services.IntServiceLibros;

@Service
public class LibroServiceJpa implements IntServiceLibros {

	@Autowired
	private LibrosRepository repoLibro;

	@Override
	public List<Libro> obtenerLibros() {
		return repoLibro.findAll();
	}

	@Override
	public void guardar(Libro libro) {
		repoLibro.save(libro);
	}

	@Override
	public Libro buscarPorId(Integer idLibro) {
		Optional<Libro> optional = repoLibro.findById(idLibro);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(Integer idLibro) {
		repoLibro.deleteById(idLibro);
		;
	}

	@Override
	public long numeroLibros() {
		return repoLibro.count();
	}

	@Override
	public Page<Libro> buscarTodas(Pageable page) {
		return repoLibro.findAll(page);
	}

	@Override
	public List<Libro> obtenerRelevantes() {
		List<Libro> relevante = new LinkedList<>();
		for (Libro libro : repoLibro.findAll()) {
			if (libro.getRelevante() == 1) {
				relevante.add(libro);
			}
		}
		return relevante;
	}

	@Override
	public List<Libro> buscarPorClas(Integer idLibro) {
		List<Libro> clasificacion = new LinkedList<>();
		for(Libro libro: repoLibro.findAll()) {
			if(libro.getClasificacion().getId() == idLibro) {
				clasificacion.add(libro);
			}
		}
		return clasificacion;
	}

}
