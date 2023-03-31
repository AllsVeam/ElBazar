package AE_Desing.ElBazar.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import AE_Desing.ElBazar.entity.Editorial;
import AE_Desing.ElBazar.repository.EditorialRepository;
import AE_Desing.ElBazar.services.IntServiceEditorial;

@Service
public class EditorialServiceJpa implements IntServiceEditorial {
	
	@Autowired EditorialRepository repoEdit;
	
	@Override
	public List<Editorial> obtenerEditorial() {		
		return repoEdit.findAll();
	}

	@Override
	public void guardar(Editorial editorial) {
		repoEdit.save(editorial);
	}

	@Override
	public Editorial buscarPorId(Integer idEditorial) {
		Optional<Editorial> optional = repoEdit.findById(idEditorial);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;		
	}

	@Override
	public void eliminar(Integer idEditorial) {
		repoEdit.delete(buscarPorId(idEditorial));		
	}

	@Override
	public int numeroEditorial() {
		return (int) repoEdit.count();
	}

	@Override
	public Page<Editorial> buscarTodas(Pageable page) {
		return repoEdit.findAll(page);
	}

}
