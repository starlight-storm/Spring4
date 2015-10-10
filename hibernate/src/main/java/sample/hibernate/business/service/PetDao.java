package sample.hibernate.business.service;

import java.util.List;

import sample.hibernate.business.domain.Pet;

public interface PetDao {
    
    Pet findById(int petId);
    
    List<Pet> findAll();
        
}
