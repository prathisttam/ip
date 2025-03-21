package simba.utils;

import simba.main.AllTasksManager;
import simba.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    public void showWelcomeMessage() {
        String logo = "____  _           _           \n" +
                "/ ___|(_)_ __ ___ | |__   __ _ \n" +
                "\\___ \\| | '_ ` _ \\| '_ \\ / _` |\n" +
                " ___) | | | | | | | |_) | (_| |\n" +
                "|____/|_|_| |_| |_|_.__/ \\__,_|";

        printDashedLine();
        System.out.println("Hello! I'm Simba");
        System.out.println("What can I do for you?\n" + logo);
        printDashedLine();
    }

    public void showExitMessage() {
        printDashedLine();
        System.out.println("Bye. Hope to see you again soon!");
        printDashedLine();
    }

    public void showMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1 - Play the game Echo!");
        System.out.println("2 - Manage Tasks");
        System.out.println("3 - Exit");
        System.out.print("Your choice: ");
    }

    public void printInvalidMenuChoice() {
        System.out.println("Invalid input! Please enter 1, 2 or 3.");
    }

    public void printDashedLine() {
        System.out.println("____________________________________________________________");
    }

    public void printEchoedCommands(String input) {
        printDashedLine();
        System.out.println(input);
        printDashedLine();
    }

    public void printMessage(String input) {
        System.out.println(input);
    }

    /**
     * Prints a single task from the task list.
     *
     * @param taskNumber The position of the task in the list (1-based index).
     */
    public void printSingleTask(int taskNumber) {
        Task currentTask = AllTasksManager.getTasksList().get(taskNumber - 1);
        System.out.println("  " + currentTask.toString());
    }

    public void printTaskList(ArrayList<Task> tasksList) {
        int taskIndex = 1;
        printDashedLine();
        for (Task task : tasksList) {
            System.out.println(taskIndex + "." + task.toString()); //new
            taskIndex += 1;
        }
        printDashedLine();
    }

    public void printTaskDeleted(Task taskToDelete, int remainingTasksNumber) {
        printDashedLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + taskToDelete.toString());
        System.out.println("Now you have " + remainingTasksNumber + " tasks in the list.");
        printDashedLine();
    }

    public void printTaskAdded(Task taskToAdd, int remainingTasksNumber) {
        printDashedLine();
        System.out.println("Got it. I've added this task:\n" + "  " + taskToAdd.toString());
        System.out.println("Now you have " + remainingTasksNumber + " tasks in the list.");
        printDashedLine();
    }

    public void printUnmarkedTask(int taskNumber) {
        printDashedLine();
        System.out.println("OK, I've marked this task as not done yet:");
        printSingleTask(taskNumber);
        printDashedLine();
    }

    public void printMarkedTask(int taskNumber) {
        printDashedLine();
        System.out.println("Nice! I've marked this task as done:");
        printSingleTask(taskNumber);
        printDashedLine();
    }

    public void showTaskPrompt() {
        System.out.println("Please enter the tasks you have yet to complete or type \"List\" to list pending tasks");
    }

    public void printErrorMessage(String message) {
        printDashedLine();
        System.out.println(message);
        printDashedLine();
    }

}
