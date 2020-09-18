package data;

import java.io.Serializable;
import java.time.LocalTime;

public class SwimResult implements Serializable, Comparable<SwimResult> {
    private String discipline;
    private String eventName;
    private int placement;
    private LocalTime time;
    private String owner;

    public SwimResult(String discipline, String eventName, int placement, LocalTime time) {
        this.discipline = discipline;
        this.eventName = eventName;
        this.placement = placement;
        this.time = time;
    }

    public int compareTo(SwimResult other) {
        return this.time.compareTo(other.time);
    }

    public String toString() {
        return "Event: " + eventName + "Name: "+owner+" -- Discipline: "+discipline+" -- Placed #"+placement+" -- @"+time+"\n";
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getPlacement() {
        return placement;
    }

    public void setPlacement(int placement) {
        this.placement = placement;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
