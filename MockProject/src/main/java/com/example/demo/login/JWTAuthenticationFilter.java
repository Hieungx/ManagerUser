package com.example.demo.login;

import com.example.demo.service.JWTTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public JWTAuthenticationFilter(String url, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                request.getParameter("username"),
                request.getParameter("password"),
                Collections.emptyList())
        );// Quyền truy cập đang trống));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        JWTTokenService.addJWTTokenHeader(response, authResult.getName());
    }
}
