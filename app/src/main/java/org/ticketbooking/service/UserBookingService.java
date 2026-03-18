package org.ticketbooking.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ticketbooking.entity.Train;
import org.ticketbooking.entity.User;
import org.ticketbooking.util.UserServiceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserBookingService {

    private User user;

    private final String USER_PATH="app/src/main/java/org/ticketbooking/localdb/users.json";

    private ObjectMapper objectMapper = new ObjectMapper();

    private List<User> userList;

    public UserBookingService(User user) throws IOException {
        this.user = user;
        loadUsers();
    }
     public UserBookingService() throws IOException{
        loadUsers();
     }


    private void loadUsers() throws IOException {
        userList = objectMapper.readValue(new File(USER_PATH), new TypeReference<List<User>>() {});
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
        try{
            TrainBookingService trainService = new TrainBookingService();
            return trainService.searchTrains(source, dest);
        }catch(IOException ex){
            return new ArrayList<>();
        }
    }

    public List<List<Integer>> fetchSeats(Train trainSelectedForBooking) {
        return trainSelectedForBooking.getSeats();
    }

    public Boolean bookTrainSeat(Train trainSelectedForBooking, int row, int seat) {

         try{
             TrainBookingService trainService = new TrainBookingService();
             List<List<Integer>> seats=trainSelectedForBooking.getSeats();
             if(row>=0 && row<seats.size() && seat>=0 && seat<seats.get(row).size()){
                 if(seats.get(row).get(seat)==0){
                     seats.get(row).set(seat,1);
                     return Boolean.TRUE;
                 }else{
                     return Boolean.FALSE;
                 }
             }
             else{
                 return  Boolean.FALSE;
             }
         } catch (Exception e) {
             return Boolean.FALSE;
         }

    }
}
