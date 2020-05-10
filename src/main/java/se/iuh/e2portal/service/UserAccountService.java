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
		
		return userAccountRepository.findAll(pageable);
	}

	public Page<UserAccount> findAll(Pageable pageable, String id) {
		return userAccountRepository.findAll(pageable,id);
	}


}
