package com.niit.PizzaService.service;

import com.niit.PizzaService.domian.Pizza;
import com.niit.PizzaService.domian.User;
import com.niit.PizzaService.exception.UserAlreadyExistException;
import com.niit.PizzaService.exception.UserNotFoundExcepyion;
import com.niit.PizzaService.proxy.UserProxy;
import com.niit.PizzaService.repository.PizzaRepository;
import com.niit.PizzaService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PizzaServiceImpl implements PizzaService {

    UserRepository userRepository;
    UserProxy userProxy;
    PizzaRepository pizzaRepository;

    @Autowired
    public PizzaServiceImpl(UserRepository userRepository, UserProxy userProxy, PizzaRepository pizzaRepository) {
        this.userRepository = userRepository;
        this.userProxy = userProxy;
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public User registerUser(User user) throws UserAlreadyExistException {
        if (userRepository.findById(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistException();
        }
        ResponseEntity response = userProxy.saveUser(user);
        return userRepository.save(user);
    }

    @Override
    public User saveUserPizzaToList(Pizza pizza, String mail) throws UserNotFoundExcepyion {
        if (userRepository.findById(mail).isEmpty()) {
            throw new UserNotFoundExcepyion();
        }
        User user = userRepository.findByEmail(mail);
        if (user.getPizzaList() == null) {
            user.setPizzaList(Arrays.asList(pizza));
        } else {
            List<Pizza> pizzas = user.getPizzaList();
            pizzas.add(pizza);
            user.setPizzaList(pizzas);
        }
        return userRepository.save(user);
    }

    @Override
    public List<Pizza> getOrderDetails(String email) throws UserNotFoundExcepyion {
        if (userRepository.findById(email).isEmpty()) {
            throw new UserNotFoundExcepyion();
        }
        return userRepository.findById(email).get().getPizzaList();
    }

    @Override
    public User deleteFromPizzaList(String email, String pizzaName) {
        User user = userRepository.findById(email).get();
        user.getPizzaList().removeIf(p -> p.getPizzaName().equals(pizzaName));

        return userRepository.save(user);
    }
}