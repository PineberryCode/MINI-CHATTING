package com.project.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;

@Getter
@Setter
public class User {
    private String id;
    private String e_mail;
    private String username;
    private String password;

    /*@Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    public Set<String> usernames;

    public User () {usernames = new HashSet<>();}*/
    
}