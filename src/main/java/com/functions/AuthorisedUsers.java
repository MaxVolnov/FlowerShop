package com.functions;

import com.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AuthorisedUsers {
    private static AuthorisedUsers instance;

    private List<User> authorisedUsers;

    public static AuthorisedUsers getInstance(){
        if (instance == null){
            instance = new AuthorisedUsers();
        }
        return instance;
    }

    public void add(User user){
        authorisedUsers.add(user);
    }

    public List<Integer> list() {
        return authorisedUsers.stream().map(User::getId).collect(Collectors.toList());
    }
}
