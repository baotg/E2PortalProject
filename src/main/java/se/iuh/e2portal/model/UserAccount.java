package se.iuh.e2portal.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class UserAccount{
    @Id
    private String accountId;
    private String password;
    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name="accountId", referencedColumnName = "personId")
    private Person person;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "userAccount_Role", joinColumns = @JoinColumn(name = "accountId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles;


}
