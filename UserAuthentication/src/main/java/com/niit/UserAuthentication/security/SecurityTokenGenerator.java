package com.niit.UserAuthentication.security;

import com.niit.UserAuthentication.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {

    public Map<String, String> tokenGenrator(User user);
}
