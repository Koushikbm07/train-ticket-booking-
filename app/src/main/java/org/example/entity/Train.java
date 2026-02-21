package org.example.entity;

import java.sql.Time;
import java.util.List;
import java.util.Map;
import java.util.Timer;

public class Train {

    private Integer trainId;

    private Integer trainNumber;

    private List<List<Boolean>> seats;

    private Map<String, Time> stationTimings;

    private List<String> stations;
}
