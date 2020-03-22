package se.iuh.e2portal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class ModuleClass {
    @Id
    private String moduleClassId;
    private String moduleClassName;
    private Date startDate;
    private Date endDate;
    private int numOfWeek; //Số tuần
    private int numOfCredit; //Số tín chỉ
    private String semester; //Học kỳ
    private String subjectName;
    @ManyToOne
    @JoinColumn(name = "lecturerId", referencedColumnName = "lecturerId")
    private Lecturer lecturer;
    @JsonIgnore
    @ManyToMany(mappedBy = "moduleClasses")
    private List<Student> students;
    @OneToMany(mappedBy = "moduleClass")
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<TimeTable> timeTables;
}
