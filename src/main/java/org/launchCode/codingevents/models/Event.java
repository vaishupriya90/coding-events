package org.launchCode.codingevents.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;


import java.util.Date;
import java.util.Objects;

public class Event {
    private int id;
    private static int nextId = 1;

    @Size(min = 3,max = 50,message = "Name must be between 3 and 50 characters!")
    @NotBlank
    private String name;

    private EventType type;

    @Size(max=500, message = "Description too long!")
    private String description;

    @NotBlank
    @Email(message = "Invalid email! Please try again!")
    private String contactEmail;

    @NotBlank(message = "Location is required!")
    private String location;

    @AssertTrue(message = "Registration is required at this time!")
    private boolean isRegistrationRequired;

    @Positive
    private int numberOfAttendees;

    @DateTimeFormat(pattern ="yyyy-mm-dd")
    private Date date;

    public Event(){
        this.id = nextId;
        nextId++;
    }

    public Event(String name,EventType type,String description,String contactEmail,String location,
                 boolean isRegistrationRequired,int numberOfAttendees,Date date) {
        this();
        this.name = name;
        this.type = type;
        this.description = description;
        this.contactEmail = contactEmail;
        this.location = location;
        this.isRegistrationRequired = isRegistrationRequired;
        this.numberOfAttendees = numberOfAttendees;
        this.date = date;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean getIsRegistrationRequired() {
        return isRegistrationRequired;
    }

    public void setIsRegistrationRequired(boolean registrationRequired) {
        isRegistrationRequired = registrationRequired;
    }

    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public void setNumberOfAttendees(int numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
