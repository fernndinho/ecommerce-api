package me.fernndinho.shop.account.models;

import com.google.common.collect.Lists;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class AccountEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String lastname;

    private String email;
    private String password;

    private AccountType type;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Lists.newArrayList(
                new SimpleGrantedAuthority( "ROLE_" + type.name() ) //ROLE_ prefix is needed so @Secured annotation works
        );
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
