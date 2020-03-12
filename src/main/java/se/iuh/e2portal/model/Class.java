package se.iuh.e2portal.model;

import lombok.Data;


import javax.persistence.*;

@Entity
@Data
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private String acronymName;
    private String className;
    private String status;
    private int year;
    @ManyToOne
    @JoinColumn(name="lecturerId", referencedColumnName = "lecturerId")
    private Lecturer headLecturer;
    @ManyToOne
    @JoinColumn(name = "facultyId", referencedColumnName = "facultyId")
    private Faculty faculty;
}
