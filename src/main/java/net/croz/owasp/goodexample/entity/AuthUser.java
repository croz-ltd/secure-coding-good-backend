package net.croz.owasp.goodexample.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "auth_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class AuthUser implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auth_user_id_generator")
    @SequenceGenerator(name = "auth_user_id_generator", sequenceName = "auth_user_id_seq",
        allocationSize = 1)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "failed_attempts")
    private Integer failedAttempts;

    @Column(name = "last_attempt")
    private LocalDateTime lastAttempt;

    @Column(name = "locked_until")
    private LocalDateTime lockedUntil;

    @Column(name = "security_question_one")
    private String securityQuestionOne;

    @Column(name = "security_question_two")
    private String securityQuestionTwo;

    @Column(name = "security_question_three")
    private String securityQuestionThree;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(Integer failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public LocalDateTime getLastAttempt() {
        return lastAttempt;
    }

    public void setLastAttempt(LocalDateTime lastAttempt) {
        this.lastAttempt = lastAttempt;
    }

    public LocalDateTime getLockedUntil() {
        return lockedUntil;
    }

    public void setLockedUntil(LocalDateTime lockedUntil) {
        this.lockedUntil = lockedUntil;
    }

    public String getSecurityQuestionOne() {
        return securityQuestionOne;
    }

    public void setSecurityQuestionOne(String securityQuestionOne) {
        this.securityQuestionOne = securityQuestionOne;
    }

    public String getSecurityQuestionTwo() {
        return securityQuestionTwo;
    }

    public void setSecurityQuestionTwo(String securityQuestionTwo) {
        this.securityQuestionTwo = securityQuestionTwo;
    }

    public String getSecurityQuestionThree() {
        return securityQuestionThree;
    }

    public void setSecurityQuestionThree(String securityQuestionThree) {
        this.securityQuestionThree = securityQuestionThree;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
