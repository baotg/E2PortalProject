package se.iuh.e2portal.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long announcementId;
    private String title;
    private String summary;
    private String contentDetail;
    private Date createdDate;
    @ManyToOne
    @JoinColumn(name = "createdBy", referencedColumnName = "lecturerId")
    private Lecturer createdBy;

}
