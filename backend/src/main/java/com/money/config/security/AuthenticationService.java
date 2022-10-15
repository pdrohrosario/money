package com.money.config.security;


import com.money.model.User;
import com.money.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService
{
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException
	{
		Optional<User> user = usuarioRepository.findUserByUserName(userName);
		if(user.isPresent()){
			return user.get();
		}

		throw new UsernameNotFoundException("Dados inválidos");
	}
}
