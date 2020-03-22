package se.iuh.e2portal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Data
@PrimaryKeyJoinColumn(name = "userId", referencedColumnName = "personId")
public class User extends Person {
    @JsonIgnore
    @OneToOne(mappedBy = "user")
    @PrimaryKeyJoinColumn
    private UserAccount userAccount;
}
