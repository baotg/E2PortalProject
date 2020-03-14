package se.iuh.e2portal.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Semester {
    @Id
    @GenericGenerator(name = "sequence_int_id", strategy = "se.iuh.e2portal.generator.IntegerGenerator")
    @GeneratedValue(generator = "sequence_int_id")
    private int semesterId;
    private int semester;
    private int year;
}

