package org.launchCode.codingevents.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Tag extends AbstractEntity{

    @Size(min = 1, max = 20, message = "This field must have minimum od 1 character and maximum of 20 characters")
    @NotBlank
    private String name;

    public Tag(@Size(min = 1,max=20,message = "This field must have minimum od 1 character and maximum of 20 characters") String name){
        this.name = name;
    }

    public Tag(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
