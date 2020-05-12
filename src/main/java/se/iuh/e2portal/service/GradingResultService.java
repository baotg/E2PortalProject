package se.iuh.e2portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.GradingResult;
import se.iuh.e2portal.model.GradingResultPK;
import se.iuh.e2portal.model.Student;
import se.iuh.e2portal.repository.GradingResultRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GradingResultService {

    @Autowired
    private GradingResultRepository gradingResultRepository;
    public Iterable<GradingResult> findByModuleClass(String id){
        return gradingResultRepository.findByModuleClassModuleClassId(id);
    }

    public GradingResult save(GradingResult gradingResult) {
        return null;
    }

    public <S extends GradingResult> Iterable<S> saveAll(Iterable<S> entities) {
        return gradingResultRepository.saveAll(entities);
    }

    public List<GradingResult> findByStudent(Student student) {
        return gradingResultRepository.findByStudent(student);
    }


    public List<GradingResult> findAll() {
        return (List<GradingResult>)gradingResultRepository.findAll();
    }

    public long count() {
        return gradingResultRepository.count();
    }



    public void delete(GradingResult object) {
        gradingResultRepository.delete(object);
    }

	public Optional<GradingResult> findById(GradingResultPK id) {
		return gradingResultRepository.findById(id);
	}
}
