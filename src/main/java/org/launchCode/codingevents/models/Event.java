package org.launchCode.codingevents.models;

import org.hibernate.engine.internal.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class Event extends AbstractEntity {


    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters!")
    @NotBlank
    private String name;

    @ManyToOne
    @NotNull(message = "Category is required")
    private EventCategory category;

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    @Valid
    private EventDetails eventDetails;

    public Event() {
    } // JPA uses this empty constructor


    public Event(String name, EventCategory category) {
        this.name = name;
        this.category = category;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventCategory getCategory() {
        return category;
    }
    public void setCategory(EventCategory category) {
        this.category = category;
    }

    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
