package simba;

public class Todo extends Task{

    public Todo(String taskDescription){
        super(taskDescription);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageString(){
        return "T | " + super.toStorageString();
    }
}
