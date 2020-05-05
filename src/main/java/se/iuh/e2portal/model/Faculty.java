package se.iuh.e2portal.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Faculty {
	
    @Id
    private String facultyId;
    private String name;
    @OneToMany(mappedBy = "faculty", cascade = CascadeType.REMOVE)
    private List<MainClass> mainClassList;
    @OneToMany(mappedBy = "faculty", cascade = CascadeType.REMOVE)
    private List<ModuleClass> moduleClasses;
    
}
