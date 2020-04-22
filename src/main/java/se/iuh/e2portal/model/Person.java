package se.iuh.e2portal.model;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Boolean gender;
    private Date dateOfBirth;
    private String address;
    private String numberPhone;
    private String email;
    private String imageProfile;

    public String getFormatedDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateOfBirth!=null?simpleDateFormat.format(dateOfBirth):"";
    }
}
