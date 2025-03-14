package simba.tasks;

public class Event extends Task {

    protected String eventStartTime;
    protected String eventEndTime;

    public Event(String taskDescription, String eventStartTime, String eventEndTime) {
        super(taskDescription);
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }
    /**
     * Returns a string representation of the event task.
     *
     * @return Formatted string including the task type, description, start time and end time.
     */
    public String toString(){
        return "[E]" + super.toString() + " (from: " + eventStartTime + " to: " + eventEndTime + ")";
    }

    public String getEventStartTime(){
        return eventStartTime;
    }

    public String getEventEndTime(){
        return eventEndTime;
    }

    /**
     * Returns a formatted string of event task "E | {task data} | {task start date} | {task due date}" for storage.
     *
     * @return A String formatted for storage.
     */
    @Override
    public String toStorageString(){
        return "E | " + super.toStorageString() + " |" + getEventStartTime() + " | " + getEventEndTime();
    }
}
