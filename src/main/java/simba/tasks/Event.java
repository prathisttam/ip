package simba.tasks;

public class Event extends Task {

    //protected String event;
    protected String eventStartTime;
    protected String eventEndTime;

    public Event(String taskDescription, String eventStartTime, String eventEndTime) {
        super(taskDescription);
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }

    public String toString(){
        return "[E]" + super.toString() + " (from: " + eventStartTime + " to: " + eventEndTime + ")";
    }

    public String getEventStartTime(){
        return eventStartTime;
    }

    public void setEventStartTime(String eventStartTime){
        this.eventStartTime = eventStartTime;
    }

    public String getEventEndTime(){
        return eventEndTime;
    }

    public void setEventEndTime(String eventEndTime){
        this.eventEndTime = eventEndTime;
    }

    @Override
    public String toStorageString(){
        return "E | " + super.toStorageString() + " |" + getEventStartTime() + " | " + getEventEndTime();
    }
}
