package AE_Desing.ElBazar.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import AE_Desing.ElBazar.entity.Usuario;

public interface IntServiceUsuario {
	public List<Usuario> obtenerUsuario();
	public void guardar (Usuario Usuario);
	public Usuario buscarPorId (Integer idUsuario);
	public void eliminar (Integer idUsuario);
	public int numeroUsuario();
	Page<Usuario>buscarTodas(Pageable page);

}
