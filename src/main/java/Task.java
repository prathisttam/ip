public class Task {
    private String taskDescription;
    private boolean isCompleted;

    //constructor when initialising a task
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isCompleted = false;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getStatusIcon() {
        return (isCompleted ? "X" : " "); // mark done task with X
    }

    public void markAsCompleted() {
        isCompleted = true;
    }

    public void markAsIncomplete() {
        isCompleted = false;
    }

}
