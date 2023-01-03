package com.money.config.security;

import com.money.model.Usuario;
import com.money.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService
{
	@Autowired
	private UsuarioService userService;
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException
	{
		Usuario user = this.userService.findUserByUserName(userName);
		if(user != null && user.getId() !=null){
			return user;
		}

		throw new UsernameNotFoundException("Dados inválidos");
	}
}
