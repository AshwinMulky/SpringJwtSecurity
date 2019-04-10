package com.ashwinmulky.security.security;

import com.ashwinmulky.security.model.JwtToken;
import com.ashwinmulky.security.model.User;
import com.ashwinmulky.security.security.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

        JwtToken jwtToken = (JwtToken) usernamePasswordAuthenticationToken;
        String token = jwtToken.getToken();

        User user = jwtUtils.validateToken(token);

        return jwtUtils.getUserDetails(user);
    }
}
