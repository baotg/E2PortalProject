package se.iuh.e2portal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Lecturer extends Person {/**
	 * 
	 */
	private static final long serialVersionUID = -6981111536736660603L;
	@OneToMany(mappedBy = "lecturer", cascade = CascadeType.REMOVE)
	List<ModuleClass> moduleClasses;
	@OneToMany(mappedBy = "lecturer", cascade = CascadeType.REMOVE)
	List<MainClass> mainClasses;
}
