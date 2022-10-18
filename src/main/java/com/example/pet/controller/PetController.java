package com.example.pet.controller;


import com.example.pet.model.Pet;
import com.example.pet.repository.AnimalRepository;
import com.example.pet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PetController {

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    PetRepository petRepository;

    @GetMapping("pets")
    String pets(Model model) {
        List<Pet> pets = petRepository.findAll();
        model.addAttribute("pets", pets);
        return "pets";
    }

    @GetMapping("delete-pet")
    String delete(int id) {
        petRepository.deleteById(id);
        return "redirect:pets";
    }

    @GetMapping("create-pet")
    String create(Model model) {
        List<Pet> pets = petRepository.findAll();
        model.addAttribute("pets", pets);
        return "create-pets";
    }

    @PostMapping("create-pet")
    String create(Pet pet) {
        petRepository.save(pet);
        return "redirect:pets";
    }

    @GetMapping("update-pet")
    String update(Model model, int id) {
        Pet pet = petRepository.findById(id).get();
        model.addAttribute("pet", pet);

        List<Pet> pets = petRepository.findAll();
        model.addAttribute("pets", pets);
        return "update-pet";
    }

    @PostMapping("update-pet")
    String update(Pet pet) {
        petRepository.save(pet);
        return "redirect:pets";
    }
}

