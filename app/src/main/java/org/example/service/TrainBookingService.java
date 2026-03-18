package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.Train;
import org.example.entity.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TrainBookingService {


    List<Train> trainList;

    private static final String TRAIN_DB_PATH="app/src/main/java/org/example/localdb/trains.json";

    private ObjectMapper objectMapper = new ObjectMapper();


    public TrainBookingService() throws IOException{
        trainList=loadTrains();
    }

    private List<Train> loadTrains() throws IOException {
        File trains = new File(TRAIN_DB_PATH);
        List<Train> trainList= objectMapper.readValue(trains,new TypeReference<List<Train>>() {});
        return trainList;
    }


    List<Train> searchTrains(String source, String dest){
        return trainList.stream().filter(train-> validTrain(train,source,dest)).collect(Collectors.toList());

    }

    private boolean validTrain(Train train, String source, String dest) {

        List<String> stations=train.getStations();

        int sourceIndex=stations.indexOf(source.toLowerCase());
        int destIndex=stations.indexOf(dest.toLowerCase());

        return sourceIndex<destIndex && sourceIndex>=0;

    }

}
