package org.launchCode.codingevents.data;

import org.launchCode.codingevents.models.Event;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EventData {
    // we need a place to put our events
    private static final Map<Integer, Event> events = new HashMap<>();

    // we need a few methods to perform few operations such as:

    // get all events
    public static Collection<Event> getAll(){
        return events.values();
    }

    // add an event
    public static void add(Event event){
        events.put(event.getId(), event);
    }

    // get a single event
    public static Event getById(int id){
        return events.get(id);
    }

    // remove an event
    public static void remove(int id){
        events.remove(id);
    }

}
