package se.iuh.e2portal.model;

import lombok.Data;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@PrimaryKeyJoinColumn(name = "studentId", referencedColumnName = "userId")
public class Student extends User {
    private String speciality;//Chuyên ngành
    private int year; //Khóa học
    @ManyToMany
    @JoinTable(name = "student_moduleClass", joinColumns = @JoinColumn(name = "classId"), inverseJoinColumns = @JoinColumn(name = "moduleClassId"))
    private List<ModuleClass> moduleClasses;
}
