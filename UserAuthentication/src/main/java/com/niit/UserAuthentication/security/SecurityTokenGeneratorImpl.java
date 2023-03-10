package com.niit.UserAuthentication.security;

import com.niit.UserAuthentication.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityTokenGeneratorImpl implements SecurityTokenGenerator{


    @Override
    public Map<String, String> tokenGenrator(User user) {
        String jwtToken = null;
        jwtToken = Jwts.builder().setSubject(user.getFirstName()).setIssuedAt(new Date()).
                    signWith(SignatureAlgorithm.HS256,"securityKey").compact();
        Map<String, String> map = new HashMap<>();
        map.put("token",jwtToken);
        map.put("message","User Logged in Successfully");
        return map;
    }
}
