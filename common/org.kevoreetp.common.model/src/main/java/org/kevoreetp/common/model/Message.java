package org.kevoreetp.common.model;

import org.lightcouch.Document;

/**
 * Created with IntelliJ IDEA.
 * User: jed
 * Date: 18/02/13
 * Time: 15:27
 * To change this template use File | Settings | File Templates.
 */
public class Message extends Document {

    public Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
