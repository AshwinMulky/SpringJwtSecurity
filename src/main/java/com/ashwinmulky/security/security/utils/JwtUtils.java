package com.ashwinmulky.security.security.utils;

import com.ashwinmulky.security.model.JwtUserDetails;
import com.ashwinmulky.security.model.User;
import com.ashwinmulky.security.model.enums.Role;
import com.ashwinmulky.security.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.omg.CORBA.ORB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    @Value("${jwt.secret.key}")
    private String secret;

    @Value("${jwt.secret.expiration}")
    private long expirationMillis;

    @Autowired
    private UserRepository userRepository;

    public String generateToken(User user) {

        Claims claims = Jwts.claims()
                .setSubject(user.getName());
        claims.put("userId", String.valueOf(user.getId()));
        claims.put("roles", user.getRoles());

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        long expMillis = nowMillis + expirationMillis;
        Date exp = new Date(expMillis);

        return Jwts.builder()
                .setIssuedAt(now)
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(exp)
                .compact();

    }

    public User validateToken(String token) {

        User user = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            //user = retrieveUser(body); //validate against DB
            //OR
            user = new User();
            user.setName(body.getSubject());
            user.setId((String) body.get("userId"));
            List<String> authorities = (List) body.get("roles");
            Set<Role> roles = authorities.stream().map(authority -> Role.valueOf(authority)).collect(Collectors.toSet());
            user.setRoles(roles);

        }
        catch (Exception e){
            throw new RuntimeException("Invalid JWT");
        }

        return user;
    }

    //use this method only if you need more security
    private User retrieveUser(Claims body) {
        String id = (String) body.get("userId");
        String name = body.getSubject();
        Optional<User> mayBeUser = userRepository.findByIdAndName(id, name);
        return mayBeUser.map(User::new).orElse(null);
    }

    public JwtUserDetails getUserDetails(User user) {
        return new JwtUserDetails(user);
    }
}
