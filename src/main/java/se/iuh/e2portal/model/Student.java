package se.iuh.e2portal.model;

import lombok.Data;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@PrimaryKeyJoinColumn(name = "studentId", referencedColumnName = "personId")
public class Student extends Person {
    @ManyToOne
    @JoinColumn(name = "classId", referencedColumnName = "acronymName")
    private Class classId;

    @ManyToMany
    @JoinTable(name = "student_moduleClass", joinColumns = @JoinColumn(name = "classId"), inverseJoinColumns = @JoinColumn(name = "moduleClassId"))
    private List<ModuleClass> moduleClasses;
}
