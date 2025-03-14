package simba.tasks;

/**
 * Represents a Todo task in the Simba task manager.
 * A Todo task is a simple task that does not have a specific deadline or event duration.
 */
public class Todo extends Task {

    public Todo(String taskDescription){
        super(taskDescription);
    }

    /**
     * Returns the string representation of the Todo task.
     *
     * @return Formatted string including the task type and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a formatted string of the deadline task "T | {task data}" for storage.
     *
     * @return A string formatted for file storage.
     */
    @Override
    public String toStorageString(){
        return "T | " + super.toStorageString();
    }
}
