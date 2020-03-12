package se.iuh.e2portal.model;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long personId;
    private String fullName;
    private String status;
    private boolean gender;
    private Date dateOfBirth;
    private String Address;
    private String phoneNumber;
    private String email;
    private String imageProfile;
//    @OneToOne(mappedBy = "person")
//    @PrimaryKeyJoinColumn
//    private UserAccount userAccount;

}
