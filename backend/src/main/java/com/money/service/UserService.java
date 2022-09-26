package com.money.service;

import com.money.model.dto.UserDetalheDTO;
import com.money.model.User;
import com.money.model.dto.UserDTO;
import com.money.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
	@Autowired
	private UserRepository userRepository;

	public UserDTO create(UserDetalheDTO form) throws Exception
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
			this.userRepository.saveUser(newUser.getUsername(), newUser.getEmail(), newUser.getName(),
				newUser.getPassword());
		}
		catch (Exception error)
		{
			throw new Exception("error by" + error.getMessage());
		}
		return UserDTO.create().withUserName(form.getUserName()).withEmail(form.getEmail());
	}

	public Boolean isUserExists(String userName)
	{
		return this.userRepository.findUserByUserName(userName).isPresent();
	}

	public UserDTO search(Long id)
	{
		User user = this.findUserById(id);
		return UserDTO.create().withUserName(user.getUsername()).withEmail(user.getEmail())
			.withId(user.getId());
	}

	public User findUserById(Long id)
	{
		Optional<User> user = this.userRepository.findUserById(id);
		return user.get();
	}

	public User findUserByUserName(String userName)
	{
		Optional<User> user = this.userRepository.findUserByUserName(userName);
		return user.get();
	}

	public UserDTO update(UserDetalheDTO form, Long userId) throws Exception
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

			this.userRepository.update(user.getName(), user.getEmail(),
				user.getPassword(), userId);

			return  UserDTO.create().withUserName(user.getUsername()).withEmail(user.getEmail())
				.withId(user.getId());
		}

		return null;
	}

	public boolean delete(Long userId)
	{
		Optional<User> user = this.userRepository.findUserById(userId);
		if (user.isPresent())
		{
			this.userRepository.delete(user.get().getId());
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
		return this.userRepository.countIdUser() + 1;
	}
}
