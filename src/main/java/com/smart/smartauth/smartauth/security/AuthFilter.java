package com.smart.smartauth.smartauth.security;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.smart.smartauth.smartauth.entities.User;
import com.smart.smartauth.smartauth.repositories.RoleUserMappingRepository;
import com.smart.smartauth.smartauth.repositories.UserRepository;
import java.io.IOException;
import java.util.Collections;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleUserMappingRepository roleUserMappingRepository;

    @Autowired
    private JwtService jwtService;

    @Value("${API_KEY}")
    private String API_KEY;

    @Value("${ADMIN_KEY}")
    private String ADMIN_KEY;

    private Object principle;
    private String role;

    private void validateJwt(String authToken) {
        String jwtToken = authToken.substring(7);
        String username = jwtService.extractUsername(jwtToken);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User doesn't exist"));
        String role = roleUserMappingRepository.findByUserid(user.getId()).get().getRole();

        this.principle = user;
        this.role = role;

    }

    private void validateAdminKey(String adminKey){
        if (ADMIN_KEY.equals(adminKey)) {
            this.principle = "ADMIN";
            this.role = "ADMIN";
        }
    }
    private void validateApiKey(String apiKey) {
        if (API_KEY.equals(apiKey)) {
            this.principle = "SERVICE";
            this.role = "SERVICE";
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        AntPathMatcher pathMatcher = new AntPathMatcher();
        String requestURI = request.getRequestURI();

        if (pathMatcher.match("/admin/**", requestURI)) {
            String authToken = request.getHeader("Authorization");
            String adminKey = request.getHeader("Admin-Key");

            if (authToken != null)
                validateJwt(authToken);

            if (adminKey != null)
                validateAdminKey(adminKey);
        }

        if (pathMatcher.match("/user/**", requestURI)) {
            String authToken = request.getHeader("Authorization");
            validateJwt(authToken);
        }

        if (pathMatcher.match("/isc/**", requestURI)) {
            String apiKey = request.getHeader("Api-Key");
            validateApiKey(apiKey);
        }

        if (principle != null && role != null) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(principle, null,
                    Collections.singleton(new SimpleGrantedAuthority(role)));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }

}
