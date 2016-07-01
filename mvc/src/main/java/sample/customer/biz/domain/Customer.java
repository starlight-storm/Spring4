package sample.customer.biz.domain;

import java.util.Date;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;

@XmlRootElement
public class Customer implements java.io.Serializable {

    private int id;

    @NotNull
    @Size(max = 20)
    private String name;

    @NotNull
    @Pattern(regexp = ".+@.+")
    private String emailAddress;

    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthday;

    @Max(9)
    @Min(0)
    private Integer favoriteNumber;

    @AssertFalse(message = "{errors.emailAddress.ng}")
    public boolean isNgEmail() {
        if (emailAddress == null) {
            return false;
        }
        // ドメイン名が「ng.foo.baz」であれば使用不可のアドレスと見なす
        return emailAddress.matches(".*@ng.foo.baz$");
    }

    public Customer() {}

    public Customer(String name, String emailAddress,
                        Date birthday, Integer favoriteNumber) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.birthday = birthday;
        this.favoriteNumber = favoriteNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Integer getFavoriteNumber() {
        return favoriteNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setFavoriteNumber(Integer favoriteNumber) {
        this.favoriteNumber = favoriteNumber;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer [id=%s, name=%s, emailAddress=%s, birthday=%s, favoriteNumber=%s]",
                id, name, emailAddress, birthday, favoriteNumber);
    }

    private static final long serialVersionUID = 5498108629060769963L;
}
