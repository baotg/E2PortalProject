package se.iuh.e2portal.model;

import lombok.Data;
import javax.persistence.*;


import java.util.Date;
@Entity
@Data
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long attendanceId;
    private Date dateOff;
    private boolean allowed;
    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "moduleClassId")
    private ModuleClass moduleClass;
}
