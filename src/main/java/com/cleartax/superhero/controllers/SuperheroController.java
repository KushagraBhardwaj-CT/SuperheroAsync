package com.cleartax.superhero.controllers;

import com.cleartax.superhero.dto.Superhero;
import com.cleartax.superhero.dto.SuperheroRequestBody;
import com.cleartax.superhero.repository.SuperheroRepository;
import com.cleartax.superhero.services.SuperheroService;
import com.cleartax.superhero.config.SqsConfig;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@RestController
public class SuperheroController {

    @Autowired
    private SuperheroRepository SuperheroRepository;

    private SuperheroService superheroService;

    @Autowired
    private SqsConfig sqsConfig;

    @Autowired
    private final SqsClient sqsClient;

    @Autowired
    public SuperheroController(SuperheroService superheroService, SqsClient sqsClient){
        this.superheroService = superheroService;
        this.sqsClient = sqsClient;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "username", defaultValue = "World") String username) {
        sqsClient.sendMessage(SendMessageRequest.builder()
                .queueUrl(sqsConfig.getQueueUrl())
                .messageBody("Kushagra").build());
        return String.format("Hello %s!, %s", username, sqsConfig.getQueueName());
    }
    @GetMapping("/superhero/{id}")
    public Superhero getSuperheroById(@PathVariable String id) {
        return SuperheroRepository.findById(id).orElseThrow(() -> new RuntimeException("Superhero not found"));
    }

    @GetMapping("/updateName/{id}/{name}")
    public String updateSuperheroSqs(@PathVariable String id, @PathVariable String name) {
        String str = id + "#" + name;
        sqsClient.sendMessage(SendMessageRequest.builder()
                .queueUrl(sqsConfig.getQueueUrl())
                .messageBody(str).build());

        return "Success";
    }

    @GetMapping("/superhero")
    public Superhero getSuperhero(@RequestParam(value = "name", defaultValue = "Batman") String name,
                                  @RequestParam(value = "universe", defaultValue = "DC") String universe){
        return superheroService.getSuperhero(name, universe);
    }

    @PostMapping("/superhero")
    public Superhero persistSuperhero(@RequestBody SuperheroRequestBody superhero){
        return superheroService.persistSuperhero(superhero);
    }

    @PutMapping("/{id}")
    public Superhero updateSuperhero(@PathVariable String id, @RequestBody Superhero superhero) {
        return superheroService.updateSuperhero(id, superhero);
    }

    @DeleteMapping("/{id}")
    public void deleteSuperhero(@PathVariable String id){
        superheroService.deleteSuperhero(id);
    }
}