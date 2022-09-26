package com.money.controller;

import com.money.config.security.TokenService;
import com.money.model.dto.LoginDTO;
import com.money.model.dto.TokenDto;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController
{
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginDTO form){
		UsernamePasswordAuthenticationToken dataLogin = form.converter();

		try
		{
			Authentication auth = this.authenticationManager.authenticate(dataLogin);
			String token = tokenService.generateToken(auth);

			return ResponseEntity.ok(new TokenDto(token,"Bearer"));
		}catch (AuthenticationException e){
			return ResponseEntity.badRequest().build();
		}


	}
}
