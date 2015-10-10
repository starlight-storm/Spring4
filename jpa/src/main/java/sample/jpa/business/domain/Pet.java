package sample.jpa.business.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Pet {
    @Id
    private Integer petId;
    private String petName;    
    @ManyToOne
    @JoinColumn(name="owner_id")
    private Owner owner;
    private Integer price;
    private Date birthDate;
        
    public Integer getPetId() {
        return petId;
    }
    public void setPetId(Integer petId) {
        this.petId = petId;
    }
    public String getPetName() {
        return petName;
    }
    public void setPetName(String petName) {
        this.petName = petName;
    }
    public Integer getPrice() {
        return price;
    }
    public Owner getOwner() {
        return owner;
    }
    public void setOwner(Owner owner) {
        this.owner = owner;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
}
