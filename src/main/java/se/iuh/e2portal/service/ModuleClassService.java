package se.iuh.e2portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.ModuleClass;
import se.iuh.e2portal.repository.ModuleClassRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleClassService  {

    @Autowired
    private ModuleClassRepository moduleClassRepository;


    public ModuleClass save(ModuleClass moduleClass) {
        return moduleClassRepository.save(moduleClass);
    }


    public Optional<ModuleClass> findById(String id) {
        return moduleClassRepository.findById(id);
    }

    public List<ModuleClass> findByName(String name) {
        return moduleClassRepository.findByName(name);
    }


    public List<ModuleClass> findAll() {
        return (List<ModuleClass>) moduleClassRepository.findAll();
    }


    public boolean existsById(String id) {
        return moduleClassRepository.existsById(id);
    }


    public long count() {
        return moduleClassRepository.count();
    }


    public void deleteById(String id) {
        moduleClassRepository.deleteById(id);
    }


    public void delete(ModuleClass object) {
        moduleClassRepository.delete(object);
    }
}
