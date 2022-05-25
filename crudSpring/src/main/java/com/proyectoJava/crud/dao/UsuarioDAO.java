package com.proyectoJava.crud.dao;

import java.util.List;



import com.proyectoJava.crud.models.Usuario;

public interface UsuarioDAO {
	
	List<Usuario> getUsuarios();

	void eliminar(Long id);

	void registrar(Usuario usuario);

	Usuario obtenerUsuarioPorCredencial(Usuario usuario);

}
 