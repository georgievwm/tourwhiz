package org.georgiev.tourwhiz.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.georgiev.tourwhiz.security.TourWhizUserPrincipal;
import org.georgiev.tourwhiz.services.AuthenticationService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final AuthenticationService authenticationService;

    public JWTAuthenticationFilter(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken token = createToken(authorizationHeader);

        SecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken createToken(String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");

        TourWhizUserPrincipal tourWhizUserPrincipal = authenticationService.parseToken(token);

        List<GrantedAuthority> authorities = new ArrayList<>(List.of(new SimpleGrantedAuthority("USER_ROLE")));

        return new UsernamePasswordAuthenticationToken(tourWhizUserPrincipal, null, authorities);
    }
}
