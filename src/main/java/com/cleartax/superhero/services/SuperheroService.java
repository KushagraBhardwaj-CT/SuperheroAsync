package com.cleartax.superhero.services;

import com.cleartax.superhero.dto.Superhero;
import com.cleartax.superhero.dto.SuperheroRequestBody;
import com.cleartax.superhero.repository.SuperheroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperheroService {

    private final SuperheroRepository superheroRepository;

    @Autowired
    public SuperheroService(SuperheroRepository superheroRepository) {
        this.superheroRepository = superheroRepository;
    }

    public Superhero getSuperhero(String name, String universe) {
        return superheroRepository.findByNameAndUniverse(name, universe);
    }

    public Superhero persistSuperhero(SuperheroRequestBody superheroRequestBody) {
        Superhero superhero = new Superhero();
        superhero.setName(superheroRequestBody.getName());
        superhero.setUniverse(superheroRequestBody.getUniverse());
        superhero.setPower(superheroRequestBody.getPower());
        return superheroRepository.save(superhero);
    }

    public Superhero getSuperheroById(String id) {
        return superheroRepository.findById(id).orElseThrow(() -> new RuntimeException("Superhero not found"));
    }

    public Superhero updateSuperhero(String id, Superhero superhero) {
        Superhero existingSuperhero = superheroRepository.findById(id).orElseThrow(() -> new RuntimeException("Superhero not found"));
        existingSuperhero.setName(superhero.getName());
        existingSuperhero.setUniverse(superhero.getUniverse());
        return superheroRepository.save(existingSuperhero);
    }

    public void deleteSuperhero(String id) {
        superheroRepository.deleteById(id);
    }

    public Superhero updateSuperheroName(String id, String newName) {
        Superhero existingSuperhero = superheroRepository.findById(id).orElseThrow(() -> new RuntimeException("Superhero not found"));
        existingSuperhero.setName(newName);
        return superheroRepository.save(existingSuperhero);
    }
}