package se.iuh.e2portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.Faculty;
import se.iuh.e2portal.repository.FacultyRepository;

import java.util.Optional;
@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;

    public <S extends Faculty> S save(S entity) {
        return facultyRepository.save(entity);
    }


    public <S extends Faculty> Iterable<S> saveAll(Iterable<S> entities) {
        return facultyRepository.saveAll(entities);
    }


    public Optional<Faculty> findById(String s) {
        return facultyRepository.findById(s);
    }


    public boolean existsById(String s) {
        return facultyRepository.existsById(s);
    }


    public Iterable<Faculty> findAll() {
        return facultyRepository.findAll();
    }


    public Iterable<Faculty> findAllById(Iterable<String> strings) {
        return facultyRepository.findAllById(strings);
    }


    public long count() {
        return facultyRepository.count();
    }


    public void deleteById(String s) {
        facultyRepository.deleteById(s);
    }


    public void delete(Faculty entity) {
        facultyRepository.delete(entity);
    }


    public void deleteAll(Iterable<? extends Faculty> entities) {
        facultyRepository.deleteAll(entities);
    }

    public void deleteAll() {
        facultyRepository.deleteAll();
    }
}
