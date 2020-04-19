package se.iuh.e2portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.Role;
import se.iuh.e2portal.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;


    public Role save(Role role) {
        return roleRepository.save(role);
    }


    public Role findById(Integer id) {
        return roleRepository.findById(id).get();
    }


    public Role findByName(String name) {
        return roleRepository.findByRoleName(name);
    }


    public List<Role> findAll() {
        return (List<Role>) roleRepository.findAll();
    }


    public boolean existsById(Integer id) {
        return roleRepository.existsById(id);
    }


    public long count() {
        return roleRepository.count();
    }


    public void deleteById(Integer id) {
        roleRepository.deleteById(id);
    }


    public void delete(Role object) {
        roleRepository.delete(object);
    }
}
