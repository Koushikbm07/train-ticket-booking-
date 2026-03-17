package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserBookingService {

    private User user;

    private final String USER_PATH="../localdb/users.json";

    private ObjectMapper objectMapper = new ObjectMapper();

    private List<User> userList;

    public UserBookingService(User user) throws IOException {
        this.user = user;
        File users = new File(USER_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});

    }

    private boolean loginUser(String username,String password){
        Optional<User> user=userList.stream().filter(user1 -> {
            return user1.getUserName().equals(username) && user1.getPassword().equals(password);
        }).findFirst();
        return user.isPresent();
    }

}
