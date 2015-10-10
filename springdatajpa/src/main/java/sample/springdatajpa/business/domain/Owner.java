package sample.springdatajpa.business.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Owner {

    @Id
    private Integer ownerId;
    private String ownerName;
    
    public Integer getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    
}
