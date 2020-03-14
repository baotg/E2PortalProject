package se.iuh.e2portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.GradingResult;
import se.iuh.e2portal.model.Student;
import se.iuh.e2portal.repository.GradingResultRepository;

import java.util.List;

@Service
public class GradingResultService implements GenericTemplate<GradingResult, Long>{

    @Autowired
    private GradingResultRepository gradingResultRepository;

    @Override
    public GradingResult save(GradingResult gradingResult) {
        return null;
    }

    @Override
    public GradingResult findById(Long id) {
        return gradingResultRepository.findById(id).get();
    }

    public List<GradingResult> findByStudent(Student student) {
        return gradingResultRepository.findByStudent(student);
    }

    @Override
    public List<GradingResult> findAll() {
        return (List<GradingResult>)gradingResultRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return gradingResultRepository.existsById(id);
    }

    @Override
    public long count() {
        return gradingResultRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        gradingResultRepository.deleteById(id);

    }

    @Override
    public void delete(GradingResult object) {
        gradingResultRepository.delete(object);
    }
}
