package com.foro.apiforo.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {   //UserDetails:se utiliza para el login
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    private String name;

    private String lastname;

    private boolean flag;

    public User(DataUserInsert insertDataUser) {
        this.flag = true;
        this.email = insertDataUser.email();
        this.password = hashPassword(insertDataUser.password());
        this.userType = insertDataUser.userType();
        this.name = insertDataUser.name();
        this.lastname = insertDataUser.lastname();
    }

    public void updateData (DataUserUpdate dataUserUpdate){
        if (dataUserUpdate.password() != null){
            this.password = dataUserUpdate.password();
        }
        if (dataUserUpdate.name() != null){
            this.name = dataUserUpdate.name();
        }
        if (dataUserUpdate.lastname() != null){
            this.name = dataUserUpdate.lastname();
        }
    }

    public void disableUser() {
        this.flag = false;
    }

    //Solo se realiza para el login
    private static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(10);
        return BCrypt.hashpw(password_plaintext, salt);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
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