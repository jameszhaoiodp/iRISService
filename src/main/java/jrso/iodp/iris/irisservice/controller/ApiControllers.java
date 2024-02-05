package jrso.iodp.iris.irisservice.controller;

import jrso.iodp.iris.irisservice.model.IRISData;
import jrso.iodp.iris.irisservice.repo.iRISRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class ApiControllers {

    @Autowired
    private iRISRepo irisRepo;

    @GetMapping(value = "/")
    public String getPage() {
        return "hello";
    }

    @GetMapping(value = "/irisdata")
    public List<IRISData> getIRISData() {

        return irisRepo.findAll();
    }

    @PostMapping(value = "/save")
    public ResponseEntity<IRISData> saveIRISData(@RequestBody IRISData irisdata) {
        try {
            IRISData _irisRow = irisRepo.save(irisdata);
            return new ResponseEntity<>(_irisRow, HttpStatus.CREATED);
        } catch (Exception e) {
            //cannot catch this exception
            System.out.println("history already exist");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //https://stackoverflow.com/questions/59344476/parse-localdatetime-string-correctly-into-spring-boot-pathvariable
    @GetMapping(value = "/getData/{timeStamp}")
    public ResponseEntity<IRISData> getData(@PathVariable String timeStamp) {

        try {
            //      Timestamp sqlTimestamp1 = Timestamp.valueOf("2023-01-04 01:43:17");
            Timestamp sqlTimestamp1 = Timestamp.valueOf(timeStamp);

            System.out.println("SqlTimestamp1: " + sqlTimestamp1);
            IRISData data = irisRepo.findById(sqlTimestamp1).get();
            return ResponseEntity.ok(data);
//            return new ResponseEntity<IRISData>(data, HttpStatus.OK);
        } catch (NoSuchElementException e) {
           return new ResponseEntity<IRISData>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<IRISData>(HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping(value = "/getData2/{timeStamp}")
//    public ResponseEntity<IRISData> getData2(@PathVariable String timeStamp) {
//
//        try {
//            //      Timestamp sqlTimestamp1 = Timestamp.valueOf("2023-01-04 01:43:17");
//            Timestamp sqlTimestamp1 = Timestamp.valueOf(timeStamp);
//
//            System.out.println("SqlTimestamp1: " + sqlTimestamp1);
//            IRISData data = irisRepo.findById(sqlTimestamp1).get();
//
//            return new ResponseEntity<>(data, HttpStatus.OK);
//
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping(value = "/getDataByTimeRange/{time1}/{time2}")
    public ResponseEntity<List<IRISData>> getDataByTimeRange(@PathVariable String time1, @PathVariable String time2) {
        try {
            List<IRISData> data = irisRepo.getIRISDataByTimeRange(time1, time2).get();
            if (data.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

//    @GetMapping(value = "/getDataByTimeRange2/{time1}/{time2}")
//    public ResponseEntity<List<IRISData>> getDataByTimeRange2(@PathVariable String time1, @PathVariable String time2) {
//
//        try {
//            List<IRISData> data = irisRepo.getIRISDataByTimeRange(time1, time2).get();
//            if (data.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(data, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//
//    }
    /*
       TODO
        1. find out query
        2. write embed SQL datetime compare   time1 < datetime < time2
        3. https://www.dariawan.com/tutorials/java/java-sql-timestamp-examples/
        4. select * from ops.ris_data  where observed_time >= to_timestamp(?, 'dd-mm-yyyy hh24:mi:ss') and observed_time <= to_timestamp(?, 'dd-mm-yyyy hh24:mi:ss') order by observed_time asc

    */


    @PutMapping(value = "/update/{timestamp}")
    public String updateUser(@PathVariable Timestamp id, @RequestBody IRISData data) {
        IRISData updateData = irisRepo.findById(id).get();
        return "Updated ...";
    }
}
