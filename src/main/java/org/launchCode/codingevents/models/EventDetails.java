package org.launchCode.codingevents.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class EventDetails extends AbstractEntity{
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

    @OneToOne(mappedBy = "eventDetails")
    private Event event;


    public EventDetails(@Size(max = 500, message = "Description too long!") String description,
                        @NotBlank @Email(message = "Invalid email! Please try again!") String contactEmail,
                        String location,
                        boolean isRegistrationRequired, int numberOfAttendees, Date date) {
        this.description = description;
        this.contactEmail = contactEmail;
        this.location = location;
        this.isRegistrationRequired = isRegistrationRequired;
        this.numberOfAttendees = numberOfAttendees;
        this.date = date;
    }
    public EventDetails(){}

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

}
