package se.iuh.e2portal.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;

@Entity
@Data
public class Subject {
    @Id
    @GenericGenerator(name = "sequence_long_id", strategy = "se.iuh.e2portal.generator.LongGenerator")
    @GeneratedValue(generator = "sequence_long_id")
    private long subjectId;
    private String subjectName;
    @ManyToOne
    @JoinColumn(name = "facultyId", referencedColumnName = "facultyId")
    private Faculty faculty;
    private int credit;

}
