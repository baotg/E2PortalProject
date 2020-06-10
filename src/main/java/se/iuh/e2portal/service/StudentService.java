package se.iuh.e2portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.MainClass;
import se.iuh.e2portal.model.Role;
import se.iuh.e2portal.model.Student;
import se.iuh.e2portal.model.UserAccount;
import se.iuh.e2portal.repository.RoleRepository;
import se.iuh.e2portal.repository.StudentRepository;
import se.iuh.e2portal.repository.UserAccountRepository;

import java.util.*;

@Service
public class StudentService{
	
	@Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private RoleService roleService;

    public <S extends Student> S save(S entity) {
        if(!userAccountRepository.existsById(entity.getId())){
            UserAccount userAccount = new UserAccount();
            Optional<UserAccount> userParent = userAccountRepository.findById(entity.getFamilyNumber());
            if(!userParent.isPresent()){
                UserAccount user = new UserAccount();
                user.setAccountId(entity.getFamilyNumber());
                user.setPassword(passwordEncoder.encode(UserAccount.DEFAULT_PASSWORD));
                Set<Role> roles = new HashSet<>();
                roles.add(roleService.findByName(Role.USER));
                roles.add(roleService.findByName(Role.PARENT));
                user.setRoles(roles);
                userAccountRepository.save(user);
            }
            userAccount.setAccountId(entity.getId());
            userAccount.setPassword(UserAccount.DEFAULT_PASSWORD);
            userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.findByName(Role.USER));
            roles.add(roleService.findByName(Role.STUDENT));
            userAccount.setRoles(roles);
            userAccountRepository.save(userAccount);
        }
        return studentRepository.save(entity);
    }

    public <S extends Student> Iterable<S> saveAll(Iterable<S> entities) {
    	for(Student student : entities) {
            if(!userAccountRepository.existsById(student.getId())){
                save(student);
            }
    	}
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

    public Optional<Student> profile() {
        return studentRepository.profile();
    }

    public List<Student> getByParent() {
       return studentRepository.getByParent();
    }
}
