package se.iuh.e2portal.model;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private String semester; //Học kỳ
    private Date startDate;
    private Date endDate;
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Lecturer lecturer;
    @ManyToMany(mappedBy = "moduleClasses")
    private List<Student> students;
    @OneToMany(mappedBy = "moduleClass")
    private List<TimeTable> timeTables;
    public String getFormattedStartDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(startDate);
    }
    public String getFormattedEndDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(endDate);
    }
}
