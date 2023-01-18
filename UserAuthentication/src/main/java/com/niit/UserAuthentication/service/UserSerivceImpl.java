package com.niit.UserAuthentication.service;

import com.niit.UserAuthentication.domain.User;
import com.niit.UserAuthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSerivceImpl implements IUserService{

    @Autowired
    UserRepository userRepository;


    @Override
    public User addUser(User user) {
        if (userRepository.findById(user.getEmail()).isEmpty()){
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public String deleteUser(String mail) {
        if (userRepository.findById(mail).isEmpty()){
            return "User does not Exist";
        }
        userRepository.deleteById(mail);
        return "User Deleted Successfully";
    }

    @Override
    public User updateUser(User user) {
        if (userRepository.findById(user.getEmail()).isEmpty()){
            return null;
        }
        User temp =userRepository.findById(user.getEmail()).get();
        temp.setFirstName(user.getFirstName());
        temp.setLastName(user.getLastName());
        temp.setPassword(user.getPassword());
        return userRepository.save(temp);
    }

    @Override
    public User loginCheck(String mail, String password) {
        //checking user is present or not
        if (userRepository.findById(mail).isPresent()){
            //fetching user object by mail id
            User user = userRepository.findById(mail).get();
            //checking password is same or not
            if (user.getPassword().equals(password)){
                //if valid return user
                return user;
            }else{
                //if not valid return null
                return null;
            }
        }else {
            //if user does not exist
            return null;
        }
    }
}
