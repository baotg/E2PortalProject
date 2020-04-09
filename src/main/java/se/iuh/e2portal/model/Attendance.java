package se.iuh.e2portal.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


import java.util.Date;
@Entity
@Data
@ToString
public class Attendance {
    @Id
    @GenericGenerator(name = "sequence_long_id", strategy = "se.iuh.e2portal.generator.LongGenerator")
    @GeneratedValue(generator = "sequence_long_id")
    private Long attendanceId;
    private Date dateOff;
    private boolean allowed;
    @ManyToOne
    @JoinColumn(name = "id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "moduleClassId")
    private ModuleClass moduleClass;
}
