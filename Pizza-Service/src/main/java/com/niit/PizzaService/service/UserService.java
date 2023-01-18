package com.niit.PizzaService.service;

import com.niit.PizzaService.domian.Pizza;
import com.niit.PizzaService.domian.User;
import com.niit.PizzaService.exception.PizzaNotFound;
import com.niit.PizzaService.exception.UserAlreadyExist;
import com.niit.PizzaService.exception.UserNotFound;

import java.util.List;

public interface UserService {

    public List<User> getAllUser();
    public User addUser(User user) throws UserAlreadyExist;
    public String deleteUser(String mail) throws UserNotFound;
    public List<User> findAllByPizzaName(String pizzaName) throws PizzaNotFound;
    public User saveUserPizzaToList(Pizza pizza,String mail) throws UserNotFound;
}
