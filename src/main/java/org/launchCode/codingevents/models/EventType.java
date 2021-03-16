package org.launchCode.codingevents.models;

public enum EventType {
    CONFERENCE("Conference"),
    MEETUP("MeetUp "),
    WORKSHOP("Workshop"),
    SOCIAL("Social");

    private final String displayName;

    EventType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

