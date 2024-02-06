package jrso.iodp.iris.irisservice.controller;

import jrso.iodp.iris.irisservice.model.IRISData;
import jrso.iodp.iris.irisservice.repo.iRISRepo;
import jrso.iodp.iris.irisservice.service.iRISService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class ApiControllers {

    @Autowired
    private iRISRepo irisRepo;

    @Autowired
    private iRISService irisservice;

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
    @GetMapping(value = "/getDataByTime/{timeStamp}")
    public  IRISData getDataByTime(@PathVariable String timeStamp) {
        IRISData data = irisservice.getDataByTime(timeStamp);
         return data;
    }
    @GetMapping(value = "/getDataByTime2")
    public  IRISData getDataByTime2(@RequestParam(value = "time", required = true) String time) {
        System.out.println(time);
        IRISData data = irisservice.getDataByTime(time);
        return data;
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
        System.out.println(time1);
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
    @GetMapping(value = "/getDataByTimeRange2")
    public ResponseEntity<List<IRISData>> getDataByTimeRange2(@RequestParam(value = "time1", required = true) String time1, @RequestParam(value = "time2", required = true) String time2) {
        System.out.println(time1);
        System.out.println(time2);
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
