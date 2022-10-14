package com.money.controller;

import com.money.model.User;
import com.money.model.dto.UsuarioDetalheDTO;
import com.money.model.dto.UsuarioDTO;
import com.money.service.UsuarioService;
import java.net.URI;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
	public Boolean verifyUserExists(@RequestBody UsuarioDTO user){
		return this.usuarioService.isUserExists(user.getUserName());
	}

	@PostMapping("/signup")
	@Transactional
	public ResponseEntity<UsuarioDTO> create( @RequestBody @Valid UsuarioDetalheDTO form, UriComponentsBuilder uriBuilder)
		throws Exception
	{
		UsuarioDTO newUser = this.usuarioService.create(form);
		if(newUser != null)
		{
			URI uri = uriBuilder.path("/user/{id}").buildAndExpand(newUser.getId()).toUri();
			return ResponseEntity.created(uri).body(newUser);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{userName}")
	public ResponseEntity<UsuarioDTO> search(@PathVariable String userName){
		UsuarioDTO dto = this.usuarioService.search(userName);
		if(dto == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/find/{userId}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Integer userId){
		UsuarioDTO dto = this.usuarioService.searchById(userId.longValue());
		if(dto == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDTO> update(@PathVariable Long id,
		@RequestBody @Valid UsuarioDetalheDTO form) throws Exception
	{
		UsuarioDTO dto = this.usuarioService.update(form, id);
		if(dto == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id)
	{
		boolean deleteUser = this.usuarioService.delete(id);
		if (!deleteUser){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
}
