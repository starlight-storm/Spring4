package sample.biz.domain;

import java.util.Date;



public class Pet {

	private int petId;
	private String petName;
	private String ownerName;
	private int price;
	private Date birthDate;
	
	public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Pet() {}
	
	public Pet(int petId, String petName) {
		this.petId = petId;
		this.petName = petName;
	}
	
	public int getPetId() {
		return petId;
	}
	public void setPetId(int petId) {
		this.petId = petId;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	
	
}
