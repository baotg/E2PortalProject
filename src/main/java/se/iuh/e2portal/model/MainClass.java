package se.iuh.e2portal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

import javax.persistence.*;

@Entity
@Data
public class MainClass {
	
    @Id
    private String classId;
    private String className;
    private String speciality; // Chuyên ngành
    private String level; // Bậc học
    private String type; // Loại hình đào tạo
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name ="facultyId",referencedColumnName = "facultyId")
    private Faculty faculty;
    private String year;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name ="lecturerId")
    private Lecturer lecturer;
    @JsonIgnore
    @OneToMany(mappedBy = "mainClass", cascade = CascadeType.REMOVE)
    private List<Student> students;
	@Override
	public String toString() {
		return "MainClass [classId=" + classId + ", className=" + className + "]";
	}

	public String getLecturerId(){
	    return lecturer.getId();
    }
}
