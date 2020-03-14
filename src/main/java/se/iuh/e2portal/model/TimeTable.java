package se.iuh.e2portal.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
public class TimeTable {
    @Id
    @GenericGenerator(name = "sequence_long_id", strategy = "se.iuh.e2portal.generator.LongGenerator")
    @GeneratedValue(generator = "sequence_long_id")
    private long timeTableId;
    @ManyToOne
    @JoinColumn(name = "moduleClassId", referencedColumnName = "moduleClassId")
    private ModuleClass moduleClass;
    private int startPeriod;
    private int endPeriod;
    private int dayOfWeek;
}
