package com.example.coffeeshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.coffeeshop.model.Coffee;

@Service
public class CoffeeService {

    private final List<Coffee> coffees = new ArrayList<>();
    // gen id ต่อเนื่อง ไม่ชนกัน
    private final AtomicLong idCounter = new AtomicLong();

    // ใส่ข้อมูลตัวอย่างตอน service ถูกสร้าง (constructor)
    public CoffeeService() {
        coffees.add(new Coffee(idCounter.incrementAndGet(), "Espresso", 45.0));
        coffees.add(new Coffee(idCounter.incrementAndGet(), "Latte", 55.0));
    }

    public List<Coffee> getAll() {
        return coffees;
    }

    public Optional<Coffee> getById(Long id) {
        return coffees.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public Coffee create(Coffee newCoffee) {
        Long newId = idCounter.incrementAndGet();
        newCoffee.setId(newId);
        coffees.add(newCoffee);
        return newCoffee;
    }

    public Optional<Coffee> update(Long id, Coffee updatedData) {
        return getById(id).map(existing -> {
            existing.setName(updatedData.getName());
            existing.setPrice(updatedData.getPrice());
            return existing;
        });
    }

    public boolean delete(Long id) {
        return coffees.removeIf(c -> c.getId().equals(id));
    }

    // ค้นหาตามชื่อ
    public List<Coffee> searchByName(String keyword) {
        return coffees.stream()
                .filter(c -> c.getName().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }
}