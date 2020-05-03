package se.iuh.e2portal.model;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 9017772019841299795L;
	@Id
    private String id;
    private String firstName;
    private String lastName;
    private Boolean gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private String address;
    private String numberPhone;
    private String email;
    private String imageProfile;

    public String getFormatedDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateOfBirth!=null?simpleDateFormat.format(dateOfBirth):"";
    }

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", dateOfBirth=" + dateOfBirth + ", address=" + address + ", numberPhone=" + numberPhone + ", email="
				+ email + ", imageProfile=" + imageProfile + "]";
	}
    
}
