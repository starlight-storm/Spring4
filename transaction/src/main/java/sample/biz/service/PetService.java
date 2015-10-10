package sample.biz.service;

import sample.biz.domain.Pet;

public interface PetService {
    void updatePet(Pet pet); 
    void updatePetProgrammaticTransaction(Pet pet); 
    void updatePetProgrammaticTransaction2(Pet pet); 
}
