package com.niit.UserAuthentication.controller;

import com.niit.UserAuthentication.domain.User;
import com.niit.UserAuthentication.security.SecurityTokenGenerator;
import com.niit.UserAuthentication.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/app/v1/")
public class UserController {

    IUserService userService;
    SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(IUserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?>saveUser(@RequestBody User user){
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
    }

    @DeleteMapping("/user/{mail}")
    public ResponseEntity<?> deleteUser(@PathVariable String mail){
        return new ResponseEntity<>(userService.deleteUser(mail),HttpStatus.OK);
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        return new ResponseEntity<>(userService.updateUser(user),HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?>loginCheck (@RequestBody User user){
        User result = userService.loginCheck(user.getEmail(), user.getPassword());
        if (result != null){
            Map<String, String> map =securityTokenGenerator.tokenGenrator(result);
            return new ResponseEntity<>(map,HttpStatus.OK);
        }else {
            return new ResponseEntity<>("invalid user or user does not exist",HttpStatus.NOT_FOUND);
        }
    }
}
