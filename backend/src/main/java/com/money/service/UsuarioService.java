package com.money.service;

import com.money.model.dto.UsuarioDetalheDTO;
import com.money.model.User;
import com.money.model.dto.UsuarioDTO;
import com.money.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService
{
	@Autowired
	private UsuarioRepository usuarioRepository;

	public UsuarioDTO create(UsuarioDetalheDTO form) throws Exception
	{
		if (this.isUserExists(form.getUserName()))
		{
			return null;
		}

		User newUser = form.converter();
		BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
		String passwordEncode = cryptPasswordEncoder.encode(newUser.getPassword());
		newUser.setPassword(passwordEncode);
		try
		{
			this.usuarioRepository.saveUser(newUser.getUsername(), newUser.getEmail(), newUser.getName(),
				newUser.getPassword());
		}
		catch (Exception error)
		{
			throw new Exception("error by" + error.getMessage());
		}
		return UsuarioDTO.create().withId(form.getId()).withUserName(form.getUserName()).withEmail(form.getEmail());
	}

	public Boolean isUserExists(String userName)
	{
		return this.usuarioRepository.findUserByUserName(userName).isPresent();
	}

	public UsuarioDTO search(String userName)
	{
		User user = this.findUserByUserName(userName);
		return UsuarioDTO.create().withUserName(user.getUsername()).withEmail(user.getEmail())
			.withId(user.getId());
	}

	public User findUserById(Long id)
	{
		Optional<User> user = this.usuarioRepository.findUserById(id);
		return user.get();
	}

	public User findUserByUserName(String userName)
	{
		Optional<User> user = this.usuarioRepository.findUserByUserName(userName);
		return user.get();
	}

	public UsuarioDTO update(UsuarioDetalheDTO form, Long userId) throws Exception
	{

		User user = this.findUserById(userId);
		if (user != null)
		{
			if (form.getName() != null)
			{
				user.setName(form.getName());
			}

			if (this.isUserExists(form.getEmail()) && !user.getEmail().equals(form.getEmail()))
			{
				return null;
			}
			if (form.getEmail() != null)
			{
				user.setEmail(form.getEmail());
			}

			if (form.getPassword() != null)
			{
				user.setPassword(generatePasswordEncode(form.getPassword()));
			}

			this.usuarioRepository.update(user.getName(), user.getEmail(),
				user.getPassword(), userId);

			return  UsuarioDTO.create().withUserName(user.getUsername()).withEmail(user.getEmail())
				.withId(user.getId());
		}

		return null;
	}

	public boolean delete(Long userId)
	{
		Optional<User> user = this.usuarioRepository.findUserById(userId);
		if (user.isPresent())
		{
			this.usuarioRepository.delete(user.get().getId());
			return true;
		}
		return false;
	}

	private String generatePasswordEncode(String password)
	{
		BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
		return cryptPasswordEncoder.encode(password);
	}

	private Integer countIdUser()
	{
		return this.usuarioRepository.countIdUser() + 1;
	}
}
