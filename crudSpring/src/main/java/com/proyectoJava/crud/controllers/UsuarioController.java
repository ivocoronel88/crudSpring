package com.proyectoJava.crud.controllers;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyectoJava.crud.dao.UsuarioDAO;
import com.proyectoJava.crud.models.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;


@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@RequestMapping(value = "api/usuario/{id}")
	public Usuario getUsuario(@PathVariable String id) {
		Usuario usuario = new Usuario();
		
		return usuario;
	}
		
	@RequestMapping(value = "api/usuarios")
	public List<Usuario> getUsuarios() {
		
		
		return usuarioDAO.getUsuarios();
	}
	
	@RequestMapping(value = "api/usuario/{id}", method = RequestMethod.DELETE)
	public void deleteUsuario(@PathVariable Long id) {
		usuarioDAO.eliminar(id);
	
	}
	
	@RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
	public void agregarUsuario(@RequestBody Usuario usuario) {
			Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
			String hash = argon2.hash(1, 1024, 1 ,usuario.getPassword());
			usuario.setPassword(hash);
			usuarioDAO.registrar(usuario);
	
	}
}
