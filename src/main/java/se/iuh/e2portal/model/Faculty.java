package se.iuh.e2portal.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Faculty {
	
    @Id
    private String facultyId;
    private String name;
    @OneToMany(mappedBy = "faculty", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<MainClass> mainClassList;
    @OneToMany(mappedBy = "faculty", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<ModuleClass> moduleClasses;
	@Override
	public String toString() {
		return "Faculty [facultyId=" + facultyId + ", name=" + name + "]";
	}
	
}
