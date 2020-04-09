package se.iuh.e2portal.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class UserAccount{
    @Id
    private String accountId;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "userAccount_Role", joinColumns = @JoinColumn(name = "accountId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles;
}
