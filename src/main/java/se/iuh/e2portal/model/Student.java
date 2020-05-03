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
	private String familyNumber;
    @ManyToOne
    @JoinColumn(name ="classId")
    private MainClass mainClass;
    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<GradingResult> gradingResults;
    @ManyToMany(mappedBy = "students", cascade = CascadeType.REMOVE)
    private List<ModuleClass> moduleClasses;
    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Attendance> attendances;
    
}
