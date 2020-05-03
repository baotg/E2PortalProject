package se.iuh.e2portal.model;

import lombok.Data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Data
@Entity
public class MainClass {
	
    @Id
    private String classId;
    private String speciality; // Chuyên ngành
    private String level; // Bậc học
    private String type; // Loại hình đào tạo
    @ManyToOne
    @JoinColumn(name ="facultyId")
    private Faculty faculty;
    private String year;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name ="lecturerId")
    private Lecturer lecturer;
    @OneToMany(mappedBy = "mainClass", cascade = CascadeType.REMOVE)
    private List<Student> students;
	@Override
	public String toString() {
		return "MainClass [classId=" + classId + ", speciality=" + speciality + ", level=" + level + ", type=" + type
				+ ", faculty=" + faculty + ", year=" + year + "]";
	}
    
    
    
}
