public class Event extends Task {

    protected String event;

    public Event(String taskDescription, String event){
        super(taskDescription);
        this.event = event;
    }

    public String getEvent(){
        return event;
    }

    public void setEvent(String event){
        this.event = event;
    }

    public String toString(){
        return "[E]" + super.toString() + " (from: " + event + ")";
    }
}
