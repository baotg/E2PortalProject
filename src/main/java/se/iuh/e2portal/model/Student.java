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
    private String headClass; //Lớp CN
//    @ManyToOne
//    @JoinColumn(name = "headLecturer", referencedColumnName = "lecturerId")
//    private Lecturer headLecturer; //GVCN
    private String speciality;//Chuyên ngành
    private int year; //Khóa học
    @ManyToMany
    @JoinTable(name = "student_moduleClass", joinColumns = @JoinColumn(name = "classId"), inverseJoinColumns = @JoinColumn(name = "moduleClassId"))
    private List<ModuleClass> moduleClasses;
}
