package jrso.iodp.iris.irisservice.service;

import jrso.iodp.iris.irisservice.model.IRISData;
import jrso.iodp.iris.irisservice.repo.iRISRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;

@Service
public class iRISService {
    @Autowired
    private iRISRepo irisRepo;

    public  IRISData getDataByTime( String timeStamp) {

        //Timestamp sqlTimestamp1 = Timestamp.valueOf("2023-01-04 01:43:17");
        Timestamp sqlTimestamp1 = Timestamp.valueOf(timeStamp);

        IRISData data = irisRepo.findById(sqlTimestamp1).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"invalid timestamp " + timeStamp));
        return data;


    }
}
