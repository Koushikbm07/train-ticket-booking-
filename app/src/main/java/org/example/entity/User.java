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

    private String hashedPssword;

    private List<Ticket> tickesBooked;

}
