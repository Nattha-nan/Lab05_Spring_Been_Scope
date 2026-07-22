package com.example.coffeeshop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.coffeeshop.model.Coffee;
import com.example.coffeeshop.service.CoffeeService;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {

    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping
    public List<Coffee> getAll() {
        return coffeeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coffee> getById(@PathVariable Long id) {
        return coffeeService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); // bonus: 404
    }

    @PostMapping
    public ResponseEntity<Coffee> create(@RequestBody Coffee newCoffee) {
        Coffee created = coffeeService.create(newCoffee);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody Coffee updatedData) {
        return coffeeService.update(id, updatedData)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removed = coffeeService.delete(id);
        return removed
                ? ResponseEntity.noContent().build()   // 204
                : ResponseEntity.notFound().build();   // 404
    }

    // Bonus endpoint: GET /coffees/search?name=...
    @GetMapping("/search")
    public List<Coffee> search(@RequestParam String name) {
        return coffeeService.searchByName(name);
    }
}