package se.iuh.e2portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.MainClass;
import se.iuh.e2portal.model.Student;
import se.iuh.e2portal.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public <S extends Student> S save(S entity) {
        return studentRepository.save(entity);
    }


    public <S extends Student> Iterable<S> saveAll(Iterable<S> entities) {
        return studentRepository.saveAll(entities);
    }


    public Optional<Student> findById(String s) {
        return studentRepository.findById(s);
    }


    public boolean existsById(String s) {
        return studentRepository.existsById(s);
    }

    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }


    public Iterable<Student> findAllById(Iterable<String> strings) {
        return studentRepository.findAllById(strings);
    }


    public long count() {
        return studentRepository.count();
    }


    public void deleteById(String s) {
        studentRepository.deleteById(s);
    }


    public void delete(Student entity) {
        studentRepository.delete(entity);
    }

    public void deleteAll(Iterable<? extends Student> entities) {
        studentRepository.deleteAll(entities);
    }

    public void deleteAll() {
        studentRepository.deleteAll();
    }

    public Iterable<Student> findAll(Sort sort) {
        return studentRepository.findAll(sort);
    }

    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public List<Student> findByMainClass(MainClass clazz) {
        return studentRepository.findByMainClass(clazz);
    }
}
