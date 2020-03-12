package se.iuh.e2portal.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int semesterId;
    private int year;
}
