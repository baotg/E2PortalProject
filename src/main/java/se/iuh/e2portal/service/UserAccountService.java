package se.iuh.e2portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.Role;
import se.iuh.e2portal.model.UserAccount;
import se.iuh.e2portal.model.UserAccountDetails;
import se.iuh.e2portal.repository.UserAccountRepository;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserAccountService implements UserDetailsService {

	@Autowired
	private UserAccountRepository userAccountRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private PersonService personService;
	@Autowired
	private StudentService studentService;

	@Transactional
	public boolean changePassword(String id, String newPassword){
		if(id.isEmpty()) return false;
		Optional<UserAccount> userAccount = userAccountRepository.findById(id);
		if(!userAccount.isPresent()) return false;
		userAccount.get().setPassword(newPassword);
		// System.out.println(newPassword);
		save(userAccount.get());
		return true;
	}
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(username.isEmpty()) throw new UsernameNotFoundException("ID Not Empty!");
		UserAccountDetails userAccount = findById(username);
		if(userAccount == null) throw new UsernameNotFoundException(username);
		return new User(userAccount.getId().toString(),userAccount.getPassword(),userAccount.getAuthorities());
	}

	public UserAccount save(UserAccount userAccount) {
		userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
		return userAccountRepository.save(userAccount);
	}

	public UserAccountDetails findById(String id) {
		Optional<UserAccount> user = userAccountRepository.findById(id);
		if (!user.isPresent()) throw new UsernameNotFoundException("Id not found :" + id);
		return new UserAccountDetails(user.get());
	}

	public boolean existsById(String id) {
		return userAccountRepository.existsById(id);
	}

	public long count() {
		return userAccountRepository.count();
	}

	public void deleteById(String id) {
		userAccountRepository.deleteById(id);
	}

	public Iterable<UserAccount> findAll(){
		return userAccountRepository.findAll();
	}

	public void delete(UserAccount userAccount) {
		userAccountRepository.delete(userAccount);
	}

	public Page<UserAccount> findAll(Pageable pageable) {
		Page<UserAccount> page = userAccountRepository.findAll(pageable);
		page.get().forEach(z->{z.setOwner(personService.getName(z.getAccountId()));
		});
		return page;
	}

	public Page<UserAccount> findAll(Pageable pageable, String id) {
		Page<UserAccount> page = userAccountRepository.findAll(pageable,id);
		page.get().forEach(x->{x.setOwner(personService.getName(x.getAccountId()));});
		return page;

	}

	public Page<UserAccount> findAllStudentById(Pageable pageable, String id) {
		Page<UserAccount> page = userAccountRepository.findAllByRoleAndId(pageable,Role.STUDENT,id);
		page.get().forEach(x->{x.setOwner(personService.getName(x.getAccountId()));});
		return page;
	}

	public  Page<UserAccount> findAllParent(Pageable pageable){
		Page<UserAccount> page = userAccountRepository.findAllByRoleName(pageable, Role.PARENT);
		page.get().forEach(x->{
				x.setOwner(studentService.getStudentNameByParent(x.getAccountId()));});
		return page;
	}

	public  Page<UserAccount> findAllParentById(Pageable pageable, String id){
		Page<UserAccount> page = userAccountRepository.findAllByRoleAndId(pageable, Role.PARENT,id);
		page.get().forEach(x->{
			x.setOwner(studentService.getStudentNameByParent(x.getAccountId()));});
		return page;
	}
	public  Page<UserAccount> findAllStudent(Pageable pageable){
		Page<UserAccount> page = userAccountRepository.findAllByRoleName(pageable, Role.STUDENT);
		page.get().forEach(x->{x.setOwner(personService.getName(x.getAccountId()));});
		return page;
	}


}
