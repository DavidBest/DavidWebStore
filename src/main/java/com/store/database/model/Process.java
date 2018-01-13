package com.store.database.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Process implements Serializable{

    private String processName;

    public Process() {
    }

    public Process(String processName) {
        this.processName = processName;
    }

    @Id
    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }
}
