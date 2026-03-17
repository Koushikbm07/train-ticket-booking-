package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Ticket {

    private Integer ticketId;

    private Integer userId;

    private String source;

    private String destination;

    private Date dateOfTravel;

    private Train train;



}
