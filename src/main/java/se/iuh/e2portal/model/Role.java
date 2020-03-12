package se.iuh.e2portal.model;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int roldeId;
    private String roleName;
    @ManyToMany(mappedBy = "roles")
    private List<UserAccount> userAccounts;
    public Role(String name){
        this.roleName = name;
    }
}
