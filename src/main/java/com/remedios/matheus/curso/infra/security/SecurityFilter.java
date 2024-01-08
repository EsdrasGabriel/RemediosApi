package com.remedios.matheus.curso.infra.security;

import com.remedios.matheus.curso.domain.usuario.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recuperarToken(request);
        if (token != null && !token.isEmpty()) {
            try {
                String subject = tokenService.getSubject(token);
                UserDetails byLogin = usuarioRepository.findByLogin(subject);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(byLogin, null, byLogin.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (RuntimeException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token inválido ou expirado.");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        System.out.println(authorization);

        if (authorization != null) {
            return authorization.replace("Bearer ", "");
        }

        return null;
    }
}
