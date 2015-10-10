package sample.jpa.business.service;

import java.util.List;

import sample.jpa.business.domain.Pet;

public interface PetDao {
    
    Pet findById(int petId);
    
    List<Pet> findAll();
    
    
}
