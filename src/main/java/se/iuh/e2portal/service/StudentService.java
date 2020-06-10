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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService{
	
	@Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private RoleRepository roleRepository;

    public <S extends Student> S save(S entity) {
        if(!userAccountRepository.existsById(entity.getId())){
            UserAccount userAccount = new UserAccount();
            Optional<UserAccount> userParent = userAccountRepository.findById(entity.getFamilyNumber());
            if(!userParent.isPresent()){
                UserAccount user = new UserAccount();
                user.setAccountId(entity.getFamilyNumber());
                user.setPassword(UserAccount.DEFAULT_PASSWORD);
                userAccountRepository.save(user);
            }
            userAccount.setAccountId(entity.getId());
            userAccount.setPassword(UserAccount.DEFAULT_PASSWORD);
            userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
            userAccount.setRoles(new HashSet<Role>(Arrays.asList(roleRepository.findByRoleName(Role.USER))));
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
