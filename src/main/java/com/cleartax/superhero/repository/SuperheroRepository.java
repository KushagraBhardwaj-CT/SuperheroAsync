package com.cleartax.superhero.repository;

import com.cleartax.superhero.dto.Superhero;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperheroRepository extends MongoRepository<Superhero, String> {
    Superhero findByNameAndUniverse(String name, String universe);
}