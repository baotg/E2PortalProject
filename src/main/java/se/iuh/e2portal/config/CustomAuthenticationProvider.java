package se.iuh.e2portal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomAuthenticationProvider implements AuthenticationProvider {
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userAccountDetails = userDetailsService.loadUserByUsername(authentication.getName());
//        System.out.println("auth name :" + authentication.getName());
//        System.out.println("pass name :" + passwordEncoder.encode(authentication.getCredentials().toString()));
//        System.out.println("user name :" + userAccountDetails.getUsername());
//        System.out.println("user pass :" + userAccountDetails.getPassword());
        UsernamePasswordAuthenticationToken result = null;
        if(userAccountDetails.getUsername().equals(authentication.getName())&&passwordEncoder.matches(authentication.getCredentials().toString(),userAccountDetails.getPassword())){
            result = new UsernamePasswordAuthenticationToken(userAccountDetails.getUsername(),userAccountDetails.getPassword(),userAccountDetails.getAuthorities());
        }
        return result;
    }
    public void setUserDetailsService(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
