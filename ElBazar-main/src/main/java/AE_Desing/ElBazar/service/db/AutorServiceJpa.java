package AE_Desing.ElBazar.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import AE_Desing.ElBazar.entity.Autor;
import AE_Desing.ElBazar.repository.AutorRepository;
import AE_Desing.ElBazar.services.IntServiceAutor;

@Service
public class AutorServiceJpa implements IntServiceAutor {

	@Autowired AutorRepository repoAutor; 
	
	@Override
	public List<Autor> obtenerAutor() {
		return repoAutor.findAll();
	}

	@Override
	public void guardar(Autor autor) {
		repoAutor.save(autor);
	}

	@Override
	public Autor buscarPorId(Integer idAutor) {
		Optional<Autor> optional = repoAutor.findById(idAutor);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(Integer idAutor) {
		repoAutor.delete(buscarPorId(idAutor));
	}

	@Override
	public int numeroAutor() {
		return (int) repoAutor.count();
	}

	@Override
	public Page<Autor> buscarTodas(Pageable page) {		
		return repoAutor.findAll(page);
	}

}
