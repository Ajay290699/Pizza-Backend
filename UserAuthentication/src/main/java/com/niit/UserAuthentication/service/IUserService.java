package com.niit.UserAuthentication.service;

import com.niit.UserAuthentication.domain.User;

import java.util.List;

public interface IUserService {

    public User addUser(User user);
    public List<User> getAllUser();
    public String deleteUser(String mail);
    public User updateUser(User user);
    public User loginCheck(String mail, String password);
}
