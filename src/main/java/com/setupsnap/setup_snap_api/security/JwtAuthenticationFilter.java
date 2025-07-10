package com.setupsnap.setup_snap_api.security;

import com.setupsnap.setup_snap_api.service.CustomerDetailsService;
import com.setupsnap.setup_snap_api.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilter {

    private final CustomerDetailsService service;
    private final JwtUtils utils;


@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException{

    HttpServletRequest http = (HttpServletRequest) request;
String header = http.getHeader("authorization");
if(header != null && header.contains("Bearer ")){
    String token = header.substring(7);
if(utils.isTokenValid(token)){
String email = utils.extractEmail(token);
    UserDetails user = service.loadUserByUsername(email);
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            user,null,user.getAuthorities()
    );
authenticationToken.setDetails(new WebAuthenticationDetailsSource());
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

}
}
chain.doFilter(request,response);
}
}
