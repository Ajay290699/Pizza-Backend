package com.niit.PizzaService.controller;

import com.niit.PizzaService.domian.Pizza;
import com.niit.PizzaService.domian.User;
import com.niit.PizzaService.exception.PizzaNotFound;
import com.niit.PizzaService.exception.UserAlreadyExist;
import com.niit.PizzaService.exception.UserNotFound;
import com.niit.PizzaService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/v2/")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<?> addUser(@RequestBody User user){
        try {
            return new ResponseEntity<>(userService.addUser(user),HttpStatus.CREATED);
        } catch (UserAlreadyExist e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/user/{mail}")
    public ResponseEntity<?> deleteUser(@PathVariable String mail){
        try {
            return new ResponseEntity<>(userService.deleteUser(mail),HttpStatus.OK);
        } catch (UserNotFound e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/user/{pizzaName}")
    public ResponseEntity<?> getPizzaByName(@PathVariable String pizzaName){
        try {
            return new ResponseEntity<>(userService.findAllByPizzaName(pizzaName),HttpStatus.FOUND);
        } catch (PizzaNotFound e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/pizza/{mail}")
    public ResponseEntity<?> saveUserPizzaToList(@RequestBody Pizza pizza, @PathVariable String mail){
        try {
            return new ResponseEntity<>(userService.saveUserPizzaToList(pizza,mail),HttpStatus.OK);
        } catch (UserNotFound e) {
            throw new RuntimeException(e);
        }
    }
}
