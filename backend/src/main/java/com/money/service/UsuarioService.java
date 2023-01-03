package com.money.service;

import com.money.model.Transferencia;
import com.money.model.dto.UsuarioDetalheDTO;
import com.money.model.Usuario;
import com.money.model.dto.UsuarioDTO;
import com.money.repository.TransferenciaRepository;
import com.money.repository.UsuarioRepository;
import io.jsonwebtoken.lang.Assert;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService
{
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario incluir(UsuarioDetalheDTO form) throws Exception
	{
		if (this.verificarUserNameExiste(form.getUserName()))
		{
			return null;
		}
		Usuario newUser = new Usuario();
		newUser.setId(this.gerarSequencial());
		newUser.setPassword(this.generatePasswordEncode(form.getPassword()));
		newUser.setEmail(form.getEmail());
		newUser.setName(form.getName());

		return this.salvar(newUser);
	}

	public Usuario salvar(Usuario usuario) throws Exception
	{
		try
		{
			return this.usuarioRepository.save(usuario);
		}
		catch (Exception error)
		{
			throw new Exception("Erro ao salvar usuário" + error.getMessage());
		}
	}

	public Boolean verificarUserNameExiste(String userName)
	{
		return this.buscarPorUsername(userName) != null;
	}

	public Usuario buscar(Long userId)
	{
		return this.buscarPorId(userId);
	}

	private Usuario buscarPorId(Long userId)
	{
		return this.usuarioRepository.findById(userId).orElse(null);
	}

	private Usuario buscarPorUsername(String userName)
	{
		return this.usuarioRepository.buscarUsuarioPorUsername(userName).orElse(null);
	}

	public Usuario update(UsuarioDetalheDTO form) throws Exception
	{

		Usuario user = this.buscarPorId(form.getId());
		Assert.notNull(user, "Usuário não encontrado");

		if (!user.getUserName().equals(form.getUserName()))
		{
			Assert.isTrue(!this.verificarUserNameExiste(form.getUserName()));
		}

		user.setUserName(form.getUserName());
		user.setEmail(form.getEmail());
		user.setName(form.getName());

		if (form.getPassword() != null )
		{
			user.setPassword(this.generatePasswordEncode(form.getPassword()));
		}

		return this.salvar(user);
	}

	public void delete(Long userId)
	{
		Usuario user = this.buscarPorId(userId);
		Assert.notNull(user, "Usuário não encontrado");
		this.usuarioRepository.delete(user);
	}

	private String generatePasswordEncode(String password)
	{
		BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
		return cryptPasswordEncoder.encode(password);
	}

	public Long gerarSequencial()
	{
		boolean idExiste = true;
		Long sequencial = null;
		Random random = new Random();
		while (idExiste)
		{
			int numero = random.nextInt(10000);
			if (this.buscarPorId((long) numero) == null)
			{
				idExiste = false;
				sequencial = (long) numero;
			}
			;
		}
		return sequencial;
	}
}


