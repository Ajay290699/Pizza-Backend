package com.niit.PizzaService.repository;

import com.niit.PizzaService.domian.Pizza;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PizzaRepository extends MongoRepository<Pizza, String> {
}
