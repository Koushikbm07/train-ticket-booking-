package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {


    private Integer userId;

    private String userName;

    private String password;

    private String hashedPassword;

    private List<Ticket> tickesBooked;


    public User(String name, String password, String hashedPassword, List<Ticket> ticketsBooked, Integer userId){
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
