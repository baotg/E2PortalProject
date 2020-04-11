package se.iuh.e2portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.Lecturer;
import se.iuh.e2portal.repository.LecturerRepository;

import java.util.List;

@Service
public class LecturerService  {

    @Autowired
    private LecturerRepository lecturerRepository;


    public Lecturer save(Lecturer lecturer) {
        return lecturerRepository.save(lecturer);
    }


    public Lecturer findById(String id) {
        return lecturerRepository.findById(id).get();
    }
//
//
//    public List<Lecturer> findByName(String name) {
//        return lecturerRepository.findByName(name);
//    }


    public List<Lecturer> findAll() {
        return (List<Lecturer>) lecturerRepository.findAll();
    }


    public boolean existsById(String id) {
        return lecturerRepository.existsById(id);
    }


    public long count() {
        return lecturerRepository.count();
    }


    public void deleteById(String id) {
        lecturerRepository.deleteById(id);
    }


    public void delete(Lecturer object) {
        lecturerRepository.delete(object);
    }

}
