package se.iuh.e2portal.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    private String personId;
    private String fullName;
    private String status;
    private boolean gender;
    private Date dateOfBirth;
    private String address;
    private String phoneNumber;
    private String email;
    private String imageProfile;
    private String faculty;
}
