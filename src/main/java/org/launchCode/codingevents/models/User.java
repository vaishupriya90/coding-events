package org.launchCode.codingevents.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class User extends AbstractEntity{

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @NotNull
    String userName;
    @NotNull
    String pwHash;

    public User (){}

    public User(@NotNull String userName, @NotNull String password) {
        this.userName = userName;
        this.pwHash = encoder.encode(password);
    }

    public String getUserName() {
        return userName;
    }

    public boolean isMatchingPassword(String password){
        return encoder.matches(password,this.pwHash);
    }
}
