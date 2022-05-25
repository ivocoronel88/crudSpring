package com.proyectoJava.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyectoJava.crud.dao.UsuarioDAO;
import com.proyectoJava.crud.models.Usuario;
import com.proyectoJava.crud.utils.JWTUtil;

@RestController
public class AuthController {
	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private JWTUtil jwtUtil;
	
	@RequestMapping(value = "api/login", method = RequestMethod.POST)
	public String login(@RequestBody Usuario usuario) {
		Usuario usuarioLogeado = usuarioDAO.obtenerUsuarioPorCredencial(usuario);
			if(usuarioLogeado!=null) {
			return jwtUtil.create(String.valueOf(usuarioLogeado.getId()), usuarioLogeado.getEmail());
			
		}
			return "FAIL";
	}
	
}
