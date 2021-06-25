package com.example.demo.login;

import com.example.demo.service.JWTTokenService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        Authentication authentication = JWTTokenService.parseTokenInformation((HttpServletResponse) request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request,response);
    }
}
