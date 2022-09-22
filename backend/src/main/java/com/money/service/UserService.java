package com.money.service;

import com.money.controller.form.UserForm;
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

	public UserDTO create(UserForm form) throws Exception
	{
		if (this.isUserExists(form.getEmail()))
		{
			return null;
		}

		User newUser = form.converter();
		BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
		String passwordEncode = cryptPasswordEncoder.encode(newUser.getPassword());
		newUser.setPassword(passwordEncode);
		try
		{
			this.userRepository.saveUser(newUser.getEmail(), newUser.getName(), newUser.getPassword());
		}
		catch (Exception error){
			throw new  Exception("error by" + error.getMessage());
		}
		return this.converter(newUser);
	}

	public Boolean isUserExists(String emailUser) {
		return this.userRepository.findUserByEmail(emailUser).isPresent();
	}

	public UserDTO findUserById(Long id){
		Optional<User> user = this.userRepository.findUserById(id);
		return user.map(this::converter).orElse(null);
	}

	public UserDTO update(UserForm form, Long userId) throws Exception
	{

		Optional<User> user = this.userRepository.findUserById(userId);
		if (user.isPresent())
		{
			if(form.getFullName() != null)
				user.get().setName(form.getFullName());

			if (this.isUserExists(form.getEmail()) && !user.get().getEmail().equals(form.getEmail()))
			{
				return null;
			}
			if(form.getEmail() != null)
				user.get().setEmail(form.getEmail());

			if(form.getPassword() != null)
				user.get().setPassword(generatePasswordEncode(form.getPassword()));

			this.userRepository.update(user.get().getName(), user.get().getEmail(), user.get().getPassword(),userId);

			return this.converter(user.get());
		}

		return null;
	}

	public boolean delete(Long userId){
		Optional<User> user = this.userRepository.findUserById(userId);
		if(user.isPresent()){
			this.userRepository.delete(user.get().getId());
			return true;
		}
		return false;
	}

	private UserDTO converter(User user){
		UserDTO dto = new UserDTO();
		dto.setEmail(user.getEmail());
		dto.setFullName(user.getName());
		dto.setId(user.getId());
		return dto;
	}

	private String generatePasswordEncode(String password){
		BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
		return cryptPasswordEncoder.encode(password);
	}

	private Integer countIdUser(){
		return this.userRepository.countIdUser()+1;
	}
}
