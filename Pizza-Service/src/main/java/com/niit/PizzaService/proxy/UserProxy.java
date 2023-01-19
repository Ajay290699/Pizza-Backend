package com.niit.PizzaService.proxy;

import com.niit.PizzaService.domian.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "User-Service",url = "localhost:8080")
public interface UserProxy {

    @PostMapping("app/v1/register")
    public ResponseEntity<?> saveUser(@RequestBody User user);
}
