package se.iuh.e2portal.model;

import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class ModuleClass {
	
    @Id
    private String moduleClassId;
    private String moduleClassName;
    private int numOfTSession; 
    private int numOfPSession; 
    private int numOfCredit; 
    private String semester; 
    private Date startDate;
    private Date endDate;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "facultyId", referencedColumnName = "facultyId")
    private Faculty faculty;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lecturerId", referencedColumnName = "id")
    private Lecturer lecturer;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "student_moduleClass", joinColumns = @JoinColumn(name = "moduleClassId"), inverseJoinColumns = @JoinColumn(name = "id"))
    private List<Student> students;
    @OneToMany(mappedBy = "moduleClass", cascade = CascadeType.REMOVE)
    private List<TimeTable> timeTables;
    @OneToMany(mappedBy = "moduleClass", cascade = CascadeType.REMOVE)
    private List<GradingResult> gradingResults;

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
