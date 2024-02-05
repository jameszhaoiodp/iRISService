package jrso.iodp.iris.irisservice.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
@Entity
@Table(name = "ris_data")// if class name is different with table name
public class IRISData {
    @Id
    @Column(name = "observed_time")
    private Timestamp observed_time;
    @Column(name="json_data")
    private String json_data;
    @Column(name="entered_on")
    private Timestamp entered_on;
    @Column(name="entered_by")
    private String entered_by;
    public Timestamp getObserved_time() {
        return observed_time;
    }

    public void setObserved_time(Timestamp observed_time) {

        this.observed_time = observed_time;
    }

    public String getJson_data() {
        return json_data;
    }

    public void setJson_data(String json_data) {
        this.json_data = json_data;
    }

    public Timestamp getEntered_on() {
        return entered_on;
    }

    public void setEntered_on(Timestamp entered_on) {
        this.entered_on = entered_on;
    }

    public String getEntered_by() {
        return entered_by;
    }

    public void setEntered_by(String entered_by) {
        this.entered_by = entered_by;
    }


}

/*
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @Column(name = "id")
    private int id;
 */
