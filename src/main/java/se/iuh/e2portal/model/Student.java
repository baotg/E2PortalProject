package se.iuh.e2portal.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.rest.core.annotation.RestResource;

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
    @RestResource(path = "main_class", rel = "main_class")
    private MainClass mainClass;
    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    @RestResource(path = "grading_results", rel = "grading_results")
    private List<GradingResult> gradingResults;
    @ManyToMany(mappedBy = "students", cascade = CascadeType.REMOVE)
    @RestResource(path = "module_classes", rel = "module_classes")
    private List<ModuleClass> moduleClasses;
    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    @RestResource(path = "attendances", rel = "attendances")
    private List<Attendance> attendances;
    
}
