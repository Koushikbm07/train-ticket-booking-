package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.Train;
import org.example.entity.User;
import org.example.util.UserServiceUtils;

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

     public UserBookingService() throws IOException{
        loadUsers();
     }
    public UserBookingService(User user) throws IOException {
        this.user = user;
        loadUsers();
    }

    private List<User> loadUsers() throws IOException {
        File users = new File(USER_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
        return userList;
    }

    private boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getUserName().equals(user.getUserName()) && UserServiceUtils.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user1){
        try{
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException {
        File usersFile = new File(USER_PATH);
        objectMapper.writeValue(usersFile, userList);
    }


    public void fetchBookings(){
        Optional<User> userFetched = userList.stream().filter(user1 -> {
            return user1.getUserName().equals(user.getUserName()) && UserServiceUtils.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        if(userFetched.isPresent()){
            userFetched.get().printTickets();
        }
    }

    public List<Train> getTrains(String source, String dest) {

    }
}
