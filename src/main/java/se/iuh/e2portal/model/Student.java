package se.iuh.e2portal.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Student extends Person {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2953742816239801419L;
	private String familyNumber; // SDT phụ huynh
    @ManyToOne
    @JoinColumn(name ="classId")
    private MainClass mainClass;
    @ManyToMany
    @JoinTable(name = "student_moduleClass", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "moduleClassId"))
    private List<ModuleClass> moduleClasses;
}
