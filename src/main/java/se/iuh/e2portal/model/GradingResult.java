package se.iuh.e2portal.model;

import lombok.Data;


import javax.persistence.*;

@Entity
@Data
@IdClass(GradingResultPK.class)
public class GradingResult {
    @Id
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Student student;
    @Id
    @ManyToOne
    @JoinColumn(name = "moduleClassId", referencedColumnName = "moduleClassId")
    private ModuleClass moduleClass;

    private float quiz1;
    private float quiz2;
    private float quiz3;
    private float quiz4;
    private float quiz5;
    private float midScore;
    private float endScore;
    private float averageScore;
    private float practiceScore1;
    private float practiceScore2;
    private float practiceScore3;
    private float practiceScore4;
    private float practiceScore5;
}
