package se.iuh.e2portal.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int facultyId;
    private String facultyName;
}
