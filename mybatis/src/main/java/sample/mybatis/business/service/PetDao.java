package sample.mybatis.business.service;

import java.util.List;

import sample.mybatis.business.domain.Pet;

public interface PetDao {
    
    Pet findById(int petId);
    
    List<Pet> findAll();
    
    
}
