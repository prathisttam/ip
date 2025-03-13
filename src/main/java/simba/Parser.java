package simba;

import java.util.Scanner;

public class Parser {
    private final Scanner scanner;

    public Parser() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public String[] parseTwoPartInput(String input) {
        return input.split(" ", 2);
    }

    public String[] parseCommand(String input) {
        String[] parts = input.trim().split(" ", 2);
        String command = parts[0].toLowerCase();
        String argument = (parts.length > 1) ? parts[1] : "";
        return new String[]{command, argument};
    }

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
     * Creates a Deadline task by extracting the task description and due date from the input string.
     *
     * @param taskDetails The input string containing the task description and due date, formatted as:
     *                    *                   "description /by dueDate".
     * @return A new Deadline object if the input is valid, otherwise returns null and prints an error message.
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
     * Creates an Event task by extracting the task description, start time, and end time from the input string.
     *
     * @param taskDetails taskDetails The input string containing the task description, start time, and end time,
     *                    *                    formatted as: "description /from startTime /to endTime".
     * @return A new Event object if the input is valid, otherwise returns null and prints an error message.
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
