package se.iuh.e2portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.Attendance;
import se.iuh.e2portal.model.Student;
import se.iuh.e2portal.repository.AttendanceRepository;

import java.util.List;
@Service
public class AttendanceService implements GenericTemplate<Attendance, Long> {
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Override
    public Attendance save(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public Attendance findById(Long id) {
        return attendanceRepository.findById(id).get();
    }

    @Override
    public List<Attendance> findAll() {
       return (List<Attendance>)attendanceRepository.findAll();
    }

    public List<Attendance> findAllByStudent(Student student) {
        return attendanceRepository.findAllByStudent(student);
    }

    @Override
    public boolean existsById(Long id) {
        return attendanceRepository.existsById(id);
    }

    @Override
    public long count() {
        return attendanceRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        attendanceRepository.deleteById(id);
    }

    @Override
    public void delete(Attendance object) {
        attendanceRepository.delete(object);
    }
}
