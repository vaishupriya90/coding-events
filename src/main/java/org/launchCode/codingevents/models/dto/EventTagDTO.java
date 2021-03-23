package org.launchCode.codingevents.models.dto;

import org.launchCode.codingevents.models.Event;
import org.launchCode.codingevents.models.Tag;

import javax.validation.constraints.NotNull;
import java.security.PublicKey;

public class EventTagDTO {

    @NotNull
    private Event event;

    @NotNull
    private Tag tag;

    public EventTagDTO(){}

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
