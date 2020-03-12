package se.iuh.e2portal.model;

import lombok.Data;


import javax.persistence.*;

@Entity
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long subjectId;
    private String subjectName;
    @ManyToOne
    @JoinColumn(name = "facultyId", referencedColumnName = "facultyId")
    private Faculty faculty;
    private int credit;

}
