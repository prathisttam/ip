import java.util.Scanner;

public class AllTasksManager {
    private Task[] taskList = new Task[100];
    private int taskListIndex = 0;
    Ui userInterface = new Ui();

    // methods to addTask, listTasks
    public void addTask(String taskDescription){
        taskList[taskListIndex] = new Task(taskDescription);
        taskListIndex += 1;
        userInterface.printDashedLine();
        System.out.println("added: " + taskDescription);
        userInterface.printDashedLine();
    }

    public void listTasks(){
        int taskIndex = 1;
        userInterface.printDashedLine();
        for (int i = 0; i < taskListIndex; i++){
            System.out.println(taskIndex + ". " + taskList[i].getTaskDescription());
            taskIndex += 1;
        }
        userInterface.printDashedLine();
    }

    public void startTaskManagement(){
        //Scanner to read input
        System.out.println("Please enter the tasks you have yet to complete or type \"List\" to list pending tasks");
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                userInterface.showExitMessage();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                listTasks();
            } else {
                addTask(input);
            }
        }
    }
}
