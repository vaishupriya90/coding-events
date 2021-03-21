package org.launchCode.codingevents.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class EventCategory extends AbstractEntity{

    @Size(min = 3,max = 50,message = "Name should have minimum of 3 characters and maximum of 50 characters")
    @NotBlank
    private String name;

    public EventCategory(String name) {
        this.name = name;
    }

    public EventCategory(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
