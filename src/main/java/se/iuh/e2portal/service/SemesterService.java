package se.iuh.e2portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.Semester;
import se.iuh.e2portal.repository.SemesterRepository;

import java.util.List;

@Service
public class SemesterService implements GenericTemplate<Semester, Integer> {

    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public Semester save(Semester semester) {
        return semesterRepository.save(semester);
    }

    @Override
    public Semester findById(Integer id) {
        return semesterRepository.findById(id).get();
    }

    @Override
    public List<Semester> findAll() {
        return (List<Semester>) semesterRepository.findAll();
    }

    @Override
    public boolean existsById(Integer id) {
        return semesterRepository.existsById(id);
    }

    @Override
    public long count() {
        return semesterRepository.count();
    }

    @Override
    public void deleteById(Integer id) {
        semesterRepository.deleteById(id);
    }

    @Override
    public void delete(Semester object) {
        semesterRepository.delete(object);
    }
}
