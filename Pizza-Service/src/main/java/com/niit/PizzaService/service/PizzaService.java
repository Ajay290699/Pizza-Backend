package com.niit.PizzaService.service;

import com.niit.PizzaService.domian.Pizza;
import com.niit.PizzaService.domian.User;
import com.niit.PizzaService.exception.UserAlreadyExistException;
import com.niit.PizzaService.exception.UserNotFoundExcepyion;

import java.util.List;

public interface PizzaService {

    public User registerUser(User user) throws UserAlreadyExistException;

    public User saveUserPizzaToList(Pizza pizza, String mail) throws UserNotFoundExcepyion;

    public List<Pizza> getOrderDetails(String email) throws UserNotFoundExcepyion;

    public User deleteFromPizzaList(String email, String pizzaName);
}
