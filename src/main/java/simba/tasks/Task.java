package simba.tasks;

public class Task {
    private String taskDescription;
    private boolean isCompleted;

    /**
     * Creates a new task with the given description.
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isCompleted = false;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Returns the status icon depending on whether a task is completed.
     *
     * @return "X" if the task is completed, otherwise a space " ".
     */
    public String getStatusIcon() {
        return (isCompleted ? "X" : " "); // mark done task with X
    }

    public void markAsCompleted() {
        isCompleted = true;
    }

    public void markAsIncomplete() {
        isCompleted = false;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + taskDescription;
    }

    public String toStorageString(){
        return (isCompleted ? "1" : "0" ) +"|" + getTaskDescription();
    }
}
