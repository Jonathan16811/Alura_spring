package br.com.alura.forum.security;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.forum.security.jwt.TokenManager;
import br.com.alura.forum.security.service.UsersService;


public class JwtAuthenticationFilter extends OncePerRequestFilter{

	private UsersService usersService;
	private TokenManager tokenManager;

	public JwtAuthenticationFilter(TokenManager tokenManager, UsersService usersService) {
		super();
		this.usersService = usersService;
		this.tokenManager = tokenManager;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServerException, IOException, ServletException {
		String jwt = getTokenFromRequest(request);
		
		if(tokenManager.isValid(jwt)) {
			Long userId = tokenManager.getUserIdFromToken(jwt);
			UserDetails userDetails = this.usersService.loadUserById(userId);
			
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, 
					userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		chain.doFilter(request, response);

	}
	
	private String getTokenFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		
		if(StringUtils.hasText(bearerToken)&&bearerToken.startsWith("Bearer"))
			return bearerToken.substring(7, bearerToken.length());
		
		return null;

	}
	
}
