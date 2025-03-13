package simba.tasks;

public class Deadline extends Task {

    protected String by;

    public Deadline(String taskDescription, String by) {
       super(taskDescription);
       this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toStorageString(){
        return "D | " + super.toStorageString() + " | " + getBy();
    }
}
