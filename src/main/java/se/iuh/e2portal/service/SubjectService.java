package se.iuh.e2portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.Subject;
import se.iuh.e2portal.repository.SubjectRepository;

import java.util.List;

@Service
public class SubjectService implements GenericTemplate<Subject, Long>{

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject findById(Long id) {
        return subjectRepository.findById(id).get();
    }

    public List<Subject> findByName(String name) {
        return subjectRepository.findByName(name);
    }

    @Override
    public List<Subject> findAll() {
        return (List<Subject>) subjectRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return subjectRepository.existsById(id);
    }

    @Override
    public long count() {
        return subjectRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public void delete(Subject object) {
        subjectRepository.delete(object);
    }
}
