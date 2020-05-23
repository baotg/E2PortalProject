package se.iuh.e2portal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class ModuleClass implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 8928562693457269605L;
	@Id
    private String moduleClassId;
    private String moduleClassName;
    private int numOfTSession; 
    private int numOfPSession; 
    private int numOfCredit; 
    private String semester;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    @Transient
    private int totalDay;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "facultyId", referencedColumnName = "facultyId")
    private Faculty faculty;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lecturerId", referencedColumnName = "id")
    private Lecturer lecturer;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "student_moduleClass", joinColumns = @JoinColumn(name = "moduleClassId"), inverseJoinColumns = @JoinColumn(name = "id"))
    private List<Student> students;
    @JsonIgnore
    @OneToMany(mappedBy = "moduleClass", cascade = CascadeType.REMOVE)
    private List<TimeTable> timeTables;
    @OneToMany(mappedBy = "moduleClass", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<GradingResult> gradingResults;
    @JsonIgnore
    @OneToMany(mappedBy = "moduleClass", cascade = CascadeType.REMOVE)
    private List<Attendance> attendances;

    public String getFormattedStartDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(startDate);
    }
    
    public String getFormattedEndDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(endDate);
    }
	@Override
	public String toString() {
		return "ModuleClass [moduleClassId=" + moduleClassId + ", moduleClassName=" + moduleClassName
				+ ", numOfTSession=" + numOfTSession + ", numOfPSession=" + numOfPSession + ", numOfCredit="
				+ numOfCredit + ", semester=" + semester + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
    
}
