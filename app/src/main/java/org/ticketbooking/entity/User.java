package org.ticketbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {


    private String userId;


    private String userName;

    private String password;

    private String hashedPassword;

    private List<Ticket> tickesBooked;


    public User(){}
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public List<Ticket> getTickesBooked() {
        return tickesBooked;
    }

    public void setTickesBooked(List<Ticket> tickesBooked) {
        this.tickesBooked = tickesBooked;
    }

    public User(String name, String password, String hashedPassword, List<Ticket> ticketsBooked, String userId){
        this.userName = name;
        this.password = password;
        this.hashedPassword = hashedPassword;
        this.tickesBooked = ticketsBooked;
        this.userId = userId;
    }

    public void printTickets(){
        for (Ticket ticket: tickesBooked){
            System.out.println("Ticket No :"+ticket.getTicketId());
        }
    }
}
