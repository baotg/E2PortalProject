package se.iuh.e2portal.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Data
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Lecturer extends Person {

}
