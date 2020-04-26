package se.iuh.e2portal.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
    @ManyToOne
    @JoinColumn(name ="lecturerId")
    private Lecturer lecturer;
    
}
