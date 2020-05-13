package se.iuh.e2portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.Lecturer;
import se.iuh.e2portal.repository.LecturerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LecturerService  {

    @Autowired
    private LecturerRepository lecturerRepository;


    public Lecturer save(Lecturer lecturer) {
        return lecturerRepository.save(lecturer);
    }


    public Optional<Lecturer> findById(String id) {
        return lecturerRepository.findById(id);
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


	public Page<Lecturer> findAll(Pageable pageable) {
		
		return lecturerRepository.findAll(pageable);
	}


	public Page<Lecturer> findAll(Pageable pageable, String id) {
		return lecturerRepository.findAll(pageable,id);
	}

}
