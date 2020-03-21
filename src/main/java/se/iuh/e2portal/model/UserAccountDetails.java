package se.iuh.e2portal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class UserAccountDetails implements UserDetails {
    private static final long serialVersionUID = 83918843410539768L;
    private UserAccount userAccount;
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Set<Role> roles = userAccount.getRoles();
        List<GrantedAuthority> list = new ArrayList<>();
        for (Role role : roles){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
            list.add(grantedAuthority);
        }
        return list;
    }

    @Override
    public String getPassword() {
        return userAccount.getPassword();
    }
    public String getId(){
        return userAccount.getAccountId();
    }
    @Override
    public String getUsername() {
        return getId().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
