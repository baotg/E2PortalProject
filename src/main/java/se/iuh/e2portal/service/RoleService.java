package se.iuh.e2portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.Role;
import se.iuh.e2portal.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService implements GenericTemplate<Role, Integer> {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findById(Integer id) {
        return roleRepository.findById(id).get();
    }


    public Role findByName(String name) {
        return roleRepository.findByRoleName(name);
    }

    @Override
    public List<Role> findAll() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public boolean existsById(Integer id) {
        return roleRepository.existsById(id);
    }

    @Override
    public long count() {
        return roleRepository.count();
    }

    @Override
    public void deleteById(Integer id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void delete(Role object) {
        roleRepository.delete(object);
    }
}
