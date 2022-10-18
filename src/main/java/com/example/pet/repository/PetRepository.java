package com.example.pet.repository;

import com.example.pet.model.Animal;
import com.example.pet.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {
}
