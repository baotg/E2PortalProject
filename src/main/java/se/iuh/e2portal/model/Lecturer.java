package se.iuh.e2portal.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Lecturer extends Person {/**
	 * 
	 */
	private static final long serialVersionUID = -6981111536736660603L;

}
