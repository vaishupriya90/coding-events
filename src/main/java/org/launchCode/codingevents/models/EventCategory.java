package org.launchCode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EventCategory {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    public EventCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public EventCategory(){}

    public void setName(String name) {
        this.name = name;
    }
}
