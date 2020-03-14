package se.iuh.e2portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.Class;
import se.iuh.e2portal.repository.ClassRepository;

import java.util.List;

@Service
public class ClassService implements GenericTemplate<Class, String>{

    @Autowired
    private ClassRepository classRepository;

    @Override
    public Class save(Class aClass) {
        return classRepository.save(aClass);
    }

    @Override
    public Class findById(String id) {
        return classRepository.findById(id).get();
    }

    public List<Class> findByName(String name) {
        return classRepository.findByName(name);
    }

    @Override
    public List<Class> findAll() {
        return (List<Class>)classRepository.findAll();
    }

    @Override
    public boolean existsById(String id) {
        return classRepository.existsById(id);
    }

    @Override
    public long count() {
        return classRepository.count();
    }

    @Override
    public void deleteById(String id) {
        classRepository.deleteById(id);

    }

    @Override
    public void delete(Class object) {
        classRepository.delete(object);
    }
}
