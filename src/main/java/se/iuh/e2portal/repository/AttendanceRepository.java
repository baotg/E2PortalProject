package se.iuh.e2portal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iuh.e2portal.model.Attendance;
import se.iuh.e2portal.model.Student;

import java.util.List;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance,Long> {
    @Query("select a from Attendance a where a.student = ?1")
    List<Attendance> findAllByStudent(Student student);
}
