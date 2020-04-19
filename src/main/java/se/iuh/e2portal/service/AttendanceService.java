package se.iuh.e2portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.Attendance;
import se.iuh.e2portal.model.Student;
import se.iuh.e2portal.repository.AttendanceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
    @Autowired
    AttendanceRepository attendanceRepository;

    public List<Attendance> findAllByStudent(Student student) {
        return attendanceRepository.findAllByStudent(student);
    }


    public <S extends Attendance> S save(S entity) {
        return attendanceRepository.save(entity);
    }

    public <S extends Attendance> Iterable<S> saveAll(Iterable<S> entities) {
        return attendanceRepository.saveAll(entities);
    }

    public Optional<Attendance> findById(Long aLong) {
        return attendanceRepository.findById(aLong);
    }

    public boolean existsById(Long aLong) {
        return attendanceRepository.existsById(aLong);
    }

    public Iterable<Attendance> findAll() {
        return attendanceRepository.findAll();
    }

    public Iterable<Attendance> findAllById(Iterable<Long> longs) {
        return attendanceRepository.findAllById(longs);
    }

    public long count() {
        return attendanceRepository.count();
    }

    public void deleteById(Long aLong) {
        attendanceRepository.deleteById(aLong);
    }

    public void delete(Attendance entity) {
        attendanceRepository.delete(entity);
    }

    public void deleteAll(Iterable<? extends Attendance> entities) {
        attendanceRepository.deleteAll(entities);
    }

    public void deleteAll() {
        attendanceRepository.deleteAll();

    }
}
