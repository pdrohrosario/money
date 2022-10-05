package com.money.config.security;


import com.money.model.User;
import com.money.repository.UsuarioRepository;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthenticationByTokenFilter extends OncePerRequestFilter
{
	private TokenService tokenService;

	private UsuarioRepository usuarioRepository;

	public AuthenticationByTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository)
	{
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, FilterChain filterChain)
		throws ServletException, IOException
	{
		String token = recToken(httpServletRequest);
		boolean isTokenValid = tokenService.isTokenValid(token);
		if(isTokenValid){
			this.authenticationClient(token);
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

	private void authenticationClient(String token)
	{
		Long userId = this.tokenService.getUserId(token);
		User user = this.usuarioRepository.findUserById(userId).get();
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	private String recToken(HttpServletRequest request){
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer")){
			return null;
		}

		return token.substring(7, token.length());
	}
}
