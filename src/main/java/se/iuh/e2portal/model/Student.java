package se.iuh.e2portal.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Student extends Person {
    private String familyNumber; // SDT phụ huynh
    @ManyToOne
    @JoinColumn(name ="classId")
    private MainClass mainClass;
    @ManyToMany
    @JoinTable(name = "student_moduleClass", joinColumns = @JoinColumn(name = "classId"), inverseJoinColumns = @JoinColumn(name = "moduleClassId"))
    private List<ModuleClass> moduleClasses;
}
