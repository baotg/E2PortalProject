package se.iuh.e2portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.MainClass;
import se.iuh.e2portal.repository.MainClassRepository;

import java.util.Optional;
@Service
public class MainClassService {
    @Autowired
    private MainClassRepository mainClassRepository;

    public Iterable<MainClass> findAll(Sort sort) {
        return mainClassRepository.findAll(sort);
    }


    public Page<MainClass> findAll(Pageable pageable) {
        return mainClassRepository.findAll(pageable);
    }


    public <S extends MainClass> S save(S entity) {
        return mainClassRepository.save(entity);
    }


    public <S extends MainClass> Iterable<S> saveAll(Iterable<S> entities) {
        return mainClassRepository.saveAll(entities);
    }


    public Optional<MainClass> findById(String s) {
        return mainClassRepository.findById(s);
    }


    public boolean existsById(String s) {
        return mainClassRepository.existsById(s);
    }


    public Iterable<MainClass> findAll() {
        return mainClassRepository.findAll();
    }


    public Iterable<MainClass> findAllById(Iterable<String> strings) {
        return mainClassRepository.findAllById(strings);
    }


    public long count() {
        return mainClassRepository.count();
    }


    public void deleteById(String s) {
        mainClassRepository.deleteById(s);
    }


    public void delete(MainClass entity) {
        mainClassRepository.delete(entity);
    }


    public void deleteAll(Iterable<? extends MainClass> entities) {
        mainClassRepository.deleteAll(entities);
    }

    public void deleteAll() {
        mainClassRepository.deleteAll();
    }
}
