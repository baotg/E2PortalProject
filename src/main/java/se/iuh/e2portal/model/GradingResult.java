package se.iuh.e2portal.model;

import lombok.Data;


import javax.persistence.*;

@Entity
@Data
@IdClass(GradingResultPK.class)
public class GradingResult {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName = "studentId")
    private Student student;
    @Id
    @ManyToOne
    @JoinColumn(name = "moduleClassId", referencedColumnName = "moduleClassId")
    private ModuleClass moduleClass;

    private float quiz1;
    private float quiz2;
    private float quiz3;
    private float quiz4;
    private float midScore;
    private float endScore;
    private float practiceScore1;
    private float practiceScore2;


    public GradingResult() {
    }
}
