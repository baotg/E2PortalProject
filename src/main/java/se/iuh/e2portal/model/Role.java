package se.iuh.e2portal.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Role {
	
	public static final String USER = "USER";
	public static final String STUDENT = "STUDENT";
	public static final String PARENT = "PARENT";
	public static final String ADMIN = "ADMIN";
    @Id
    @GenericGenerator(name = "sequence_int_id", strategy = "se.iuh.e2portal.generator.IntegerGenerator")
    @GeneratedValue(generator = "sequence_int_id")
    private int roleId;
    private String roleName;
    @ManyToMany(mappedBy = "roles")
    private List<UserAccount> userAccounts;
    
    
    public Role(String name){
        this.roleName = name;
    }
}
