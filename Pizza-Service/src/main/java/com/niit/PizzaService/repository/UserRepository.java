package com.niit.PizzaService.repository;

import com.niit.PizzaService.domian.User;
import com.niit.PizzaService.exception.PizzaNotFound;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    @Query("{'pizzas.pizzaName':{$in:[?0]}}")
    public List<User> findAllByPizzaName(String pizzaName) throws PizzaNotFound;
}
