package org.launchCode.codingevents.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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

    @Size(max = 500, message = "Description too long!")
    private String description;

    @NotBlank
    @Email(message = "Invalid email! Please try again!")
    private String contactEmail;

    @NotBlank(message = "Location is required!")
    private String location;

    //@AssertTrue(message = "Registration is required at this time!")
    private boolean isRegistrationRequired;

    @Positive
    private int numberOfAttendees;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date date;

    public Event() {
    } // JPA uses this empty constructor


    public Event(String name, EventCategory category, String description, String contactEmail, String location,
                 boolean isRegistrationRequired, int numberOfAttendees, Date date) {
        this.name = name;
        this.category = category;
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

    public EventCategory getCategory() {
        return category;
    }
    public void setCategory(EventCategory category) {
        this.category = category;
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

    @Override
    public String toString() {
        return this.name;
    }

}
