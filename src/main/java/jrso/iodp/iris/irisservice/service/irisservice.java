package jrso.iodp.iris.irisservice.service;

import jrso.iodp.iris.irisservice.model.IRISData;
import jrso.iodp.iris.irisservice.repo.iRISRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class irisservice {
    @Autowired
    private iRISRepo irisRepo;

//    public List<IRISData> getIRISDataByTimeRange(String time1, String time2){
//      Optional<List<IRISData>> xxx = Optional.of(irisRepo.getIRISDataByTimeRange(time1, time2).get());
//      return xxx;
//    }



//    public AddressResponse findAddressByEmployeeId(int employeeId) {
//        Optional<Address> addressFound = addressRepo.findAddressByEmployeeId(employeeId);
//        AddressResponse addressResponse = mapper.map(addressFound, AddressResponse.class);
//
//        return addressResponse;
//    }
}
