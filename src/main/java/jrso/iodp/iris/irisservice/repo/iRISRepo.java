package jrso.iodp.iris.irisservice.repo;

import jrso.iodp.iris.irisservice.model.IRISData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface iRISRepo extends JpaRepository<IRISData, Timestamp> {
    //  value= "SELECT ea.id, ea.city, ea.state FROM gfgmicroservicesdemo.address ea join gfgmicroservicesdemo.employee e on e.id = ea.employee_id where ea.employee_id=:employeeId")
    @Query( nativeQuery = true,
            value= "select * from ris_data  where observed_time >= STR_TO_DATE(:time1,'%Y-%m-%d %H:%i:%s') and observed_time <=  STR_TO_DATE(:time2,'%Y-%m-%d %H:%i:%s') order by observed_time asc")
    Optional <List<IRISData>> getIRISDataByTimeRange(@Param("time1") String time1, @Param("time2") String time2);

    //this is test
    @Query(nativeQuery = true, value = "SELECT u FROM ris_data u WHERE CONCAT(u.firstName, ' ', u.lastName) LIKE %:keyword%")
    List<IRISData> findUsersByFullNameKeyword(@Param("keyword") String keyword);

    //this is test
    @Query(value = "SELECT * FROM products p WHERE p.price > :minPrice", nativeQuery = true)
    List<IRISData> findProductsAboveMinPrice(@Param("minPrice") BigDecimal minPrice);
}
