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

    /**
     * Returns a string representation of the deadline task.
     *
     * @return Formatted string including the task type, description, and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a formatted string of the deadline task "D | {task data} | {task due by date}" for storage.
     *
     * @return A string formatted for file storage.
     */
    @Override
    public String toStorageString() {
        return "D | " + super.toStorageString() + " | " + getBy();
    }
}
