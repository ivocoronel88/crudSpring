package com.proyectoJava.crud.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoJava.crud.models.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@Repository
@Transactional
public class UsuarioDAOImp implements UsuarioDAO{
	
	@PersistenceContext
	private EntityManager entity;
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Usuario> getUsuarios() {
		String query = "FROM Usuario";
		return entity.createQuery(query).getResultList();		
	}


	@Override
	public void eliminar(Long id) {
		Usuario usuario = entity.find(Usuario.class, id);
		entity.remove(usuario);
	}


	@Override
	public void registrar(Usuario usuario) {
		entity.merge(usuario);
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public Usuario obtenerUsuarioPorCredencial(Usuario usuario) {
		String query = "FROM Usuario WHERE email = :email";
		List<Usuario>listaUsuario =entity.createQuery(query).setParameter("email", usuario.getEmail())
				.getResultList();
		if(listaUsuario.isEmpty())
			return null;
		
		String passwordHashed = listaUsuario.get(0).getPassword();
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		if(argon2.verify(passwordHashed, usuario.getPassword())) {
			return listaUsuario.get(0);
		}else {
			return null;
		}
		
		
		
	}
	
	

}
