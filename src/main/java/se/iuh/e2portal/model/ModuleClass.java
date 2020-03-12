package se.iuh.e2portal.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class ModuleClass {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private String moduleClassId;
    private String moduleClassName;
    private Date startDate;
    private int duration;
    @ManyToOne
    @JoinColumn(name = "subjectId", referencedColumnName = "subjectId")
    private Subject subject;
    @ManyToOne
    @JoinColumn(name = "lecturerId", referencedColumnName = "lecturerId")
    private Lecturer lecturer;
    @ManyToOne
    @JoinColumn(name = "semesterId", referencedColumnName = "semesterId")
    private Semester semester;
    @ManyToMany(mappedBy = "moduleClasses")
    private List<Student> students;
}
