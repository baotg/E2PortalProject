package se.iuh.e2portal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@IdClass(GradingResultPK.class)
public class GradingResult implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4047795934575386735L;
	@Id
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id", referencedColumnName = "id")
    @JsonIgnore
    private Student student;
    @Id
    @ManyToOne
    @JoinColumn(name = "moduleClassId", referencedColumnName = "moduleClassId")

    @JsonIgnore
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

    public String getModuleClassId(){
        return moduleClass.getModuleClassId();
    }
    public String getStudentId(){
        return student.getId();
    }

	@Override
	public String toString() {
		return "GradingResult [StudentId=" + student.getId() + " ModuleClassId=" + moduleClass.getModuleClassId() + "]";
	}
}
