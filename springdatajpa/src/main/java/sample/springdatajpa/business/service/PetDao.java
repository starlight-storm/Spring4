package sample.springdatajpa.business.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sample.springdatajpa.business.domain.Pet;

@Repository
public interface PetDao extends JpaRepository<Pet, Integer>, PetDaoCustom {
    List<Pet> findByPetName(String petName);
    List<Pet> findByPetNameAndPriceLessThanEqual(String petName, int price);
    
    @Query("select p from Pet p where p.owner.ownerName = :ownerName")
    List<Pet> findByOwnerName(@Param("ownerName") String ownerName);
    
    @Modifying
    @Query("update Pet p set p.price = ?1 where p.petName = ?2")
    int updatePetPrice(Integer price, String petName);
    
    
}
