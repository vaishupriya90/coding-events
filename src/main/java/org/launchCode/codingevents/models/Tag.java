package org.launchCode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag extends AbstractEntity{

    @Size(min = 1, max = 20, message = "This field must have minimum od 1 character and maximum of 20 characters")
    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "tags")
    private final List<Event> events = new ArrayList<>();

    public Tag(@Size(min = 1,max=20,message = "This field must have minimum od 1 character and maximum of 20 characters") String name){
        this.name = name;
    }

    public Tag(){}

    public String getName() {
        return name;
    }

    public String displayName(){
        return "#"+this.getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
