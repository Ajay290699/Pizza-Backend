package com.niit.PizzaService.service;

import com.niit.PizzaService.domian.Pizza;
import com.niit.PizzaService.domian.User;
import com.niit.PizzaService.exception.PizzaNotFound;
import com.niit.PizzaService.exception.UserAlreadyExist;
import com.niit.PizzaService.exception.UserNotFound;
import com.niit.PizzaService.proxy.UserProxy;
import com.niit.PizzaService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    UserRepository userRepository;
    UserProxy userProxy;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserProxy userProxy) {
        this.userRepository = userRepository;
        this.userProxy = userProxy;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) throws UserAlreadyExist {
        if (userRepository.findById(user.getEmail()).isEmpty()){
            userProxy.saveUser(user);
            return userRepository.save(user);
        }
        throw new UserAlreadyExist();
    }

    @Override
    public String deleteUser(String mail) throws UserNotFound {
        if (userRepository.findById(mail).isEmpty()){
            throw new UserNotFound();
        }
        userRepository.deleteById(mail);
        return "User Deleted Successfully";
    }

    @Override
    public List<User> findAllByPizzaName(String pizzaName) throws PizzaNotFound {
        if (userRepository.findAllByPizzaName(pizzaName).isEmpty()){
            throw new PizzaNotFound();
        }
        return userRepository.findAllByPizzaName(pizzaName);
    }

    @Override
    public User saveUserPizzaToList(Pizza pizza, String mail) throws UserNotFound {
        if (userRepository.findById(mail).isEmpty()){
            throw new UserNotFound();
        }
        User result =userRepository.findById(mail).get();
        if (result.getPizzas() != null){
            result.getPizzas().add(pizza);
        }else {
            result.setPizzas(new ArrayList<>());
            result.getPizzas().add(pizza);
        }
        userRepository.save(result);
        return result;
    }
}
