package se.iuh.e2portal.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class ModuleClass {
    @Id
    private String moduleClassId;
    private String moduleClassName;
    private int numOfTSession; //Số tiết lý thuyết
    private int numOfPSession; //Số tiết thực hành
    private int numOfCredit; //Số tín chỉ
    private int semester; //Học kỳ
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Lecturer lecturer;
    @ManyToMany(mappedBy = "moduleClasses")
    private List<Student> students;
//    @OneToMany(mappedBy = "moduleClass")
//    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
//    private List<TimeTable> timeTables;
}
