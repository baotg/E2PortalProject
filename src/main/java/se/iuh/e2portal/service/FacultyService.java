package se.iuh.e2portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.Faculty;
import se.iuh.e2portal.repository.FacultyRepository;

import java.util.List;

@Service
public class FacultyService implements GenericTemplate<Faculty, Integer> {

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public Faculty save(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findById(Integer id) {
        return facultyRepository.findById(id).get();
    }

    public List<Faculty> findByName(String name) {
        return facultyRepository.findByName(name);
    }

    @Override
    public List<Faculty> findAll() {
        return (List<Faculty>) facultyRepository.findAll();
    }

    @Override
    public boolean existsById(Integer id) {
        return facultyRepository.existsById(id);
    }

    @Override
    public long count() {
        return facultyRepository.count();
    }

    @Override
    public void deleteById(Integer id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public void delete(Faculty object) {
        facultyRepository.delete(object);
    }
}
