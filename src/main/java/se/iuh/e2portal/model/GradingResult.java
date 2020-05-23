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
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id", referencedColumnName = "id")
    @JsonIgnore
    private Student student;
    @Id
    @ManyToOne
    @JoinColumn(name = "moduleClassId", referencedColumnName = "moduleClassId")
    @JsonIgnore
    private ModuleClass moduleClass;
    private Float quiz1;
    private Float quiz2;
    private Float quiz3;
    private Float quiz4;
    private Float quiz5;
    private Float midScore;
    private Float endScore;
    private Float averageScore;
    private Float practiceScore1;
    private Float practiceScore2;
    private Float practiceScore3;
    private Float practiceScore4;
    private Float practiceScore5;

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
