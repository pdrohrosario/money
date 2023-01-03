package com.money.controller;

import com.money.model.Usuario;
import com.money.model.dto.UsuarioDetalheDTO;
import com.money.model.dto.UsuarioDTO;
import com.money.service.UsuarioService;
import java.net.URI;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/user")
public class UsuarioController
{
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/exists")
	public Boolean verificarUserNameExiste(@RequestBody UsuarioDTO user){
		return this.usuarioService.verificarUserNameExiste(user.getUserName());
	}

	@Transactional
	@PostMapping("/signup")
	public ResponseEntity<UsuarioDTO> incluir(@RequestBody @Valid UsuarioDetalheDTO form) throws Exception
	{
		try
		{
			Usuario newUser = this.usuarioService.incluir(form);
			return ResponseEntity.ok(UsuarioDTO.converter(newUser));
		}
		catch (Exception ex)
		{
			return ResponseEntity.badRequest().build();
		}

	}

	@GetMapping("/{userId}")
	public ResponseEntity<UsuarioDetalheDTO> findById(@PathVariable Long userId){
		Usuario usuario = this.usuarioService.buscar(userId);
		return ResponseEntity.ok(UsuarioDetalheDTO.converter(usuario));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDTO> update(
		@RequestBody UsuarioDetalheDTO form) throws Exception
	{
		try
		{
			Usuario newUser = this.usuarioService.update(form);
			return ResponseEntity.ok(UsuarioDTO.converter(newUser));
		}
		catch (Exception ex)
		{
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity delete(@PathVariable Long id)
	{
		try
		{
			this.usuarioService.delete(id);
			return ResponseEntity.ok().build();
		}
		catch (Exception ex)
		{
			return ResponseEntity.badRequest().build();
		}
	}
}
