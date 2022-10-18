package com.example.pet.controller;

import com.example.pet.model.Animal;
import com.example.pet.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AnimalController {
    @Autowired
    AnimalRepository animalRepository;
    @GetMapping
    String animals(Model model) {
        List<Animal> animals = animalRepository.findAll();
        model.addAttribute("animals", animals);
        return "animals";
    }

    @GetMapping("delete-animal")
    String delete(int id) {
        animalRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("create-animal")
    String create() {
        return "create-animal";
    }

    @PostMapping("create-animal")
    String create(Animal animal) {
        animalRepository.save(animal);
        return "redirect:/";
    }

    @GetMapping("update-animal")
    String update(Model model, int id) {
        Animal animal = animalRepository.findById(id).get();
        model.addAttribute("animal", animal);
        return "update-animal";
    }

    @PostMapping("update-animal")
    String update(Animal animal) {
        animalRepository.save(animal);
        return "redirect:/";
    }
}
