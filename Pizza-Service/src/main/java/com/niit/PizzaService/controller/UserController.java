package com.niit.PizzaService.controller;

import com.niit.PizzaService.domian.Pizza;
import com.niit.PizzaService.domian.User;
import com.niit.PizzaService.exception.UserAlreadyExistException;
import com.niit.PizzaService.exception.UserNotFoundExcepyion;
import com.niit.PizzaService.repository.PizzaRepository;
import com.niit.PizzaService.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/v2/")
public class UserController {

    PizzaService pizzaService;
    PizzaRepository pizzaRepository;


    @Autowired
    public UserController(PizzaService pizzaService, PizzaRepository pizzaRepository) {
        this.pizzaService = pizzaService;
        this.pizzaRepository = pizzaRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(pizzaService.registerUser(user), HttpStatus.CREATED);
        } catch (UserAlreadyExistException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/user/{email}/pizzaList")
    public ResponseEntity<?> saveUserPizzaToList(@RequestBody Pizza pizza, @PathVariable String email) {
        try {
            return new ResponseEntity<>(pizzaService.saveUserPizzaToList(pizza, email), HttpStatus.CREATED);
        } catch (UserNotFoundExcepyion e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/order/{email}")
    public ResponseEntity<?> getAllPizzaOrdered(@PathVariable String email) {
        try {
            return new ResponseEntity<>(pizzaService.getOrderDetails(email), HttpStatus.OK);
        } catch (UserNotFoundExcepyion e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete/{email}/{pizzaName}")
    public ResponseEntity<?> deleteFromPizzaList(@PathVariable String email, @PathVariable String pizzaName) {
        return new ResponseEntity<>(pizzaService.deleteFromPizzaList(email, pizzaName), HttpStatus.OK);
    }
}
