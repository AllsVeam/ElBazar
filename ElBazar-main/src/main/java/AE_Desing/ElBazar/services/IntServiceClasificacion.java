package AE_Desing.ElBazar.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import AE_Desing.ElBazar.entity.Clasificacion;

public interface IntServiceClasificacion {
		
	public List<Clasificacion> obtenerClasificaciones();
	public void guardar (Clasificacion clasificacion);
	public Clasificacion buscarPorId (Integer idClasificacion);
	public void eliminar (Integer idClasificacion);
	public int numeroClasificacion();
	Page<Clasificacion>buscarTodas(Pageable page);
}
