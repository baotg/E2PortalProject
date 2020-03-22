package se.iuh.e2portal.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Data
@PrimaryKeyJoinColumn(name = "adminId", referencedColumnName = "userId")
public class Administrator extends User {
}
