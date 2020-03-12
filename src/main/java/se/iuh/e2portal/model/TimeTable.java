package se.iuh.e2portal.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long timeTableId;
    @ManyToOne
    @JoinColumn(name = "moduleClassId", referencedColumnName = "moduleClassId")
    private ModuleClass moduleClass;
    private int startPeriod;
    private int endPeriod;
    private int dayOfWeek;
}
