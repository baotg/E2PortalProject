package se.iuh.e2portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.Lecturer;
import se.iuh.e2portal.repository.LecturerRepository;

import java.util.List;

@Service
public class LecturerService implements GenericTemplate<Lecturer, Long> {

    @Autowired
    private LecturerRepository lecturerRepository;

    @Override
    public Lecturer save(Lecturer lecturer) {
        return lecturerRepository.save(lecturer);
    }

    @Override
    public Lecturer findById(Long id) {
        return lecturerRepository.findById(id).get();
    }


    public List<Lecturer> findByName(String name) {
        return lecturerRepository.findByName(name);
    }

    @Override
    public List<Lecturer> findAll() {
        return (List<Lecturer>) lecturerRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return lecturerRepository.existsById(id);
    }

    @Override
    public long count() {
        return lecturerRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        lecturerRepository.deleteById(id);
    }

    @Override
    public void delete(Lecturer object) {
        lecturerRepository.delete(object);
    }
}
