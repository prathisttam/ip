package simba;

import simba.tasks.Deadline;
import simba.tasks.Event;
import simba.tasks.Task;
import simba.tasks.Todo;

import java.util.Scanner;

/**
 * Handles user input parsing for task creation and command execution.
 * Extracts meaningful commands and task details from raw input.
 */
public class Parser {
    private final Scanner scanner;

    public Parser() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Splits an input string into two parts at the first space.
     *
     * @param input Input string to be split.
     * @return Array containing the first word and the remaining string.
     */
    public String[] parseCommand(String input) {
        String[] parts = input.trim().split(" ", 2);
        String command = parts[0].toLowerCase();
        String argument = (parts.length > 1) ? parts[1] : "";
        return new String[]{command, argument};
    }

    /**
     * Parses a task creation command such as todo, event or deadline.
     *
     * @param taskDescription User input containing the task type and details.
     * @return A Task object representing the created task.
     */
    public Task parseTaskCreation(String taskDescription) throws SimbaException {
        String[] parts = taskDescription.split(" ", 2); // To separate out type of task

        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new SimbaException("Invalid Command :( Please provide a valid task description.");
        }

        String taskType = parts[0].toLowerCase();
        String taskDetails = parts[1];

        switch (taskType) {
        case "todo":
            return new Todo(taskDetails);
        case "deadline":
            return parseDeadline(taskDetails);
        case "event":
            return parseEvent(taskDetails);
        default:
            throw new SimbaException("Invalid command to add tasks! \n" + "Valid Commands:todo, deadline, event");
        }
    }

    /**
     * Parses a Deadline task by extracting the task description and due date from the input string.
     *
     * @param taskDetails Input string formatted as "description /by dueDate".
     * @return A new Deadline object.
     * @throws SimbaException if input format is invalid.
     */
    private Task parseDeadline(String taskDetails) throws SimbaException {
        String[] parts = taskDetails.split(" /by ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new SimbaException("Invalid deadline format!\n"
                    + "Usage: deadline <description> /by <date string>\n"
                    + "Example: deadline Submit report /by sunday");
        }
        return new Deadline(parts[0], parts[1]);
    }

    /**
     * Parses an Event task by extracting the task description, start time, and end time from the input string.
     *
     * @param taskDetails Input string formatted as: "description /from startTime /to endTime".
     * @return A new Event object.
     * @throws SimbaException if input format is invalid.
     */
    private Task parseEvent(String taskDetails) throws SimbaException {
        String[] parts = taskDetails.split(" /from ", 2);
        if (parts.length < 2) {
            throw new SimbaException("Invalid event format!\n"
                    + "Usage: event <description> /from <start /to end time string>\n"
                    + "Example: event Team meeting /from 10:00 AM /to 12:00 PM");
        }
        String eventDescription = parts[0].trim();
        String[] timeParts = parts[1].split(" /to ", 2);

        if (timeParts.length < 2) {
            throw new SimbaException("Invalid time format!\n"
                    + "Usage: time <start /to end time string>\n"
                    + "Example: event project meeting /from Mon 2pm /to 4pm");
        }

        String startTime = timeParts[0];
        String endTime = timeParts[1];

        return new Event(eventDescription, startTime, endTime);
    }

    /**
     * Parses a task number from user input.
     */
    public int parseTaskNumber(String argument, int taskListSize) throws SimbaException {
        if (argument.isEmpty()){
            throw new SimbaException("Invalid task number format!");
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new SimbaException("Invalid task number format!");
        }

        if (taskNumber <= 0 || taskNumber > taskListSize) {
            throw new SimbaException("Invalid task number format!");
        }

        return taskNumber;
    }

    /**
     * Converts a stored task string into a Task object.
     *
     * @param taskLine Raw string representing a stored task.
     * @return The reconstructed Task object.
     * @throws SimbaException If the format is invalid or missing required components.
     */
    public Task convertStringInStorageToTask(String taskLine) throws SimbaException {
        String[] parts = taskLine.split("\\|");

        if (parts.length < 3) {
            throw new SimbaException("Invalid task entered by user previously.\n");
        }

        String taskType = parts[0].trim();
        boolean isCompleted = parts[1].trim().equals("1");
        String description = parts[2].trim();

        switch (taskType) {
        case "T":
            Todo todo = new Todo(description);
            if (isCompleted) {
                todo.markAsCompleted();
            }
            return todo;
        case "D":
            if (parts.length < 4) {
                throw new SimbaException("Corrupt deadline, skipping: " + taskLine);
            }

            String by = parts[3].trim();
            Deadline deadline = new Deadline(description, by);

            if (isCompleted) {
                deadline.markAsCompleted();
            }
            return deadline;
        case "E":
            if (parts.length < 5) {
                throw new SimbaException("Corrupt event, skipping: " + taskLine);
            }

            String from = parts[3].trim();
            String to = parts[4].trim();
            Event event = new Event(description, from, to);

            if (isCompleted) {
                event.markAsCompleted();
            }

            return event;
        default:
            throw new SimbaException("Unknown task type. Skipping task " + taskType);
        }
    }

    public void closeScanner() {
        scanner.close();
    }
}
