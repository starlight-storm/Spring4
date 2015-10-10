package sample.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sample.biz.dao.PetDao;
import sample.biz.domain.Pet;

@Repository
public class PetDaoSpringJdbc implements PetDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void updatePet(Pet pet) {
        jdbcTemplate.update(
            "UPDATE PET SET PET_NAME=?, OWNER_NAME=?, PRICE=?, BIRTH_DATE=? WHERE PET_ID=?"
            , pet.getPetName(), pet.getOwnerName(), pet.getPrice(), pet.getBirthDate(), pet.getPetId());
    }
}
