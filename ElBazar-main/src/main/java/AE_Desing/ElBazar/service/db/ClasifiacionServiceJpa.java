package AE_Desing.ElBazar.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import AE_Desing.ElBazar.entity.Clasificacion;
import AE_Desing.ElBazar.repository.ClasificacionRepository;
import AE_Desing.ElBazar.services.IntServiceClasificacion;

@Service
public class ClasifiacionServiceJpa implements IntServiceClasificacion {
	
	@Autowired ClasificacionRepository repoClasific;
	
	@Override
	public List<Clasificacion> obtenerClasificaciones() {		
		return repoClasific.findAll();
	}

	@Override
	public void guardar(Clasificacion clasificacion) {
		repoClasific.save(clasificacion);
	}

	@Override
	public Clasificacion buscarPorId(Integer idClasificacion) {
		Optional<Clasificacion> optional = repoClasific.findById(idClasificacion);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;		
	}

	@Override
	public void eliminar(Integer idClasificacion) {
		repoClasific.delete(buscarPorId(idClasificacion));		
	}

	@Override
	public int numeroClasificacion() {
		return (int) repoClasific.count();
	}

	@Override
	public Page<Clasificacion> buscarTodas(Pageable page) {
		return repoClasific.findAll(page);
	}

}
