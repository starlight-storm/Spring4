package sample.mybatis.business.domain;

import java.util.Date;


public class Pet {
    private Integer petId;
    private String petName;    
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
