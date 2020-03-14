package se.iuh.e2portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.ModuleClass;
import se.iuh.e2portal.repository.ModuleClassRepository;

import java.util.List;

@Service
public class ModuleClassService implements GenericTemplate<ModuleClass, String> {

    @Autowired
    private ModuleClassRepository moduleClassRepository;

    @Override
    public ModuleClass save(ModuleClass moduleClass) {
        return moduleClassRepository.save(moduleClass);
    }

    @Override
    public ModuleClass findById(String id) {
        return moduleClassRepository.findById(id).get();
    }

    public List<ModuleClass> findByName(String name) {
        return moduleClassRepository.findByName(name);
    }

    @Override
    public List<ModuleClass> findAll() {
        return (List<ModuleClass>) moduleClassRepository.findAll();
    }

    @Override
    public boolean existsById(String id) {
        return moduleClassRepository.existsById(id);
    }

    @Override
    public long count() {
        return moduleClassRepository.count();
    }

    @Override
    public void deleteById(String id) {
        moduleClassRepository.deleteById(id);
    }

    @Override
    public void delete(ModuleClass object) {
        moduleClassRepository.delete(object);
    }
}
