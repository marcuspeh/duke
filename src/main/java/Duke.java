import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Main file for chatbot.
 *
 * @author marcuspeh
 * @version A-Enums
 * @since 15 Aug 2021
 */

public class Duke {
    /** For the chatboard to read the user input. */
    private Scanner sc;
    /** Stores all the task. */
    private List<Task> taskList;

    /**
     * Constructor for Duke.
     */
    Duke() {
        sc = new Scanner(System.in);
        taskList = new ArrayList<>();
    }

    /**
     * Start the chatbot and get it to chat with the user.
     */
    private void chat() {
        greetMessage();

        String message;
        while (true) {
            message = sc.nextLine().strip();
            String command = message.split(" ")[0].toLowerCase();

            if (command.equals(Keyword.EXIT.getKeyword()))
                break;
            else if (command.equals(Keyword.LIST.getKeyword()))
                listTask();
            else if (command.equals(Keyword.DONE.getKeyword()))
                try {
                    markDone(Integer.parseInt(message.substring(Keyword.DONE.length() + 1)));
                } catch (NumberFormatException e) {
                    doneErrorMessage();
                } catch (IndexOutOfBoundsException e) {
                    doneIndexErrorMessage();
                }
            else if (command.equals(Keyword.DEADLINE.getKeyword()))
                try {
                    String[] details = message.split(Keyword.DEADLINE.getSeparator());
                    addDeadline(details[0].substring(Keyword.DEADLINE.length() + 1), details[1]);
                } catch (IndexOutOfBoundsException e) {
                    deadlineErrorMessage();
                }
            else if (command.equals(Keyword.EVENTS.getKeyword()))
                try {
                    String[] details = message.split(Keyword.EVENTS.getSeparator());
                    addEvent(details[0].substring(Keyword.EVENTS.length() + 1), details[1]);
                } catch (IndexOutOfBoundsException e) {
                    eventErrorMessage();
                }
            else if (command.equals(Keyword.TODOS.getKeyword()))
                try {
                    addTodo(message.substring(Keyword.TODOS.length() + 1));
                } catch (IndexOutOfBoundsException e) {
                    todoErrorMessage();
                }
            else if (command.equals(Keyword.DELETE.getKeyword()))
                try {
                    deleteTask(Integer.parseInt(message.substring(Keyword.DELETE.length() + 1)));
                } catch (NumberFormatException e) {
                    deleteErrorMessage();
                } catch (IndexOutOfBoundsException e) {
                    deleteIndexErrorMessage();
                }
            else
                chatErrorMessage();
        }
        exitMessage();
    }

    /**
     * Add the task entered by the user into the list.
     *
     * @param task task input by the user
     */
    private void addTask(Task task) {
        taskList.add(task);
        printMessage("Got it. I've added this task:",
                task.toString(),
                String.format("Now you have %o task(s).", taskList.size()));
    }

    /**
     * Adds a new Event to the task list.
     *
     * @param s Description of the task.
     * @param dateTime Date and time of the event.
     */
    private void addEvent(String s, String dateTime) {
        try {
            addTask(new Events(s, dateTime));
        } catch (ParseException e) {
            dateTimeErrorMessage();
        }
    }

    /**
     * Adds a new deadline to the task list.
     *
     * @param s Description of the task
     * @param dateTime Deadline of the task
     */
    private void addDeadline(String s, String dateTime) {
        try {
            addTask(new Deadlines(s, dateTime));
        } catch (ParseException e) {
            dateTimeErrorMessage();
        }
    }

    /**
     * Add a new todo to the task list.
     *
     * @param s Description of the task.
     */
    private void addTodo(String s) {
        addTask(new ToDos(s));
    }

    /**
     * List out all the task stored by the user.
     */
    private void listTask() {
        if (taskList.size() == 0) {
            printMessage("You have no task.");
            return;
        }

        String[] task = IntStream.range(0, this.taskList.size())
                    .mapToObj(x -> (x + 1) + ". " + taskList.get(x).toString())
                    .collect(Collectors.toList())
                    .toArray(new String[0]);

        printMessage(task);
    }

    /**
     * Mark the nth task as done.
     *
     * @param n the task to be mark as done.
     */
    private void markDone(int n) {
        Task task = taskList.get(n - 1);
        boolean success = task.markDone();
        if (success)
            printMessage("Nice! I've did mark this task as done:", task.toString());
        else
            printMessage("Ugh! This task was already done:", task.toString());
    }

    /**
     * Delete the nth task from the task list.
     *
     * @param n the task to be deleted.
     */
    private void deleteTask(int n) {
        Task task = taskList.remove(n - 1);
        printMessage("Noted. I've removed this task:",
                task.toString(),
                String.format("Now you have %o task(s).", taskList.size()));
    }

    /**
     * Print out the greeting message used when the chat started.
     */
    private void greetMessage() {
        printMessage("Good day there! I'm DUKE\n", "What can I do for you?");
    }

    /**
     * Echos the message the user sends for level-1.
     *
     * @param s Message user sent.
     * @Deprecated Level-2
     */
    private void echoMessage(String s) {
        printMessage(s);
    }

    /**
     * Print out the exit message when chat ends.
     */
    private void exitMessage() {
        printMessage("Farewell! Hope to see you again.");
    }

    /**
     * Prints out chat error message when command is not recognized.
     */
    private void chatErrorMessage() {
        printMessage("Ugh! Only the following commands are recognised.",
                "bye - Ends the chat session.",
                "todo <description> - Adds a new todo to the task list.",
                "deadline <description> /by <date/time> - Adds a new deadline to the task list",
                "event <description> /at <date/time> - Adds a new event to the task list",
                "list - return a list of all the task",
                "done <number> - Sets the task to be done",
                "delete <number> - Delete the task");
    }

    /**
     * Prints out error message if done message does not contains number.
     */
    private void doneErrorMessage() {
        printMessage("Ugh! The command should be in this format:",
                "done <number>");
    }

    /**
     * Prints out error message if done message is out of range.
     */
    private void doneIndexErrorMessage() {
        printMessage("Ugh! The command should be in this format:",
                "done <number>",
                "Note: number is based on the number from command 'list'");
    }

    /**
     * Prints out error message if todo message does not contains description.
     */
    private void todoErrorMessage() {
        printMessage("Ugh! The command should be in this format:",
                "todo <description>");
    }

    /**
     * Prints out error message if deadline message does not contains /by.
     */
    private void deadlineErrorMessage() {
        printMessage("Ugh! The command should be in this format:",
                "deadline <description> /by <date/time>");
    }

    /**
     * Prints out error message if delete message does not contains number.
     */
    private void deleteErrorMessage() {
        printMessage("Ugh! The command should be in this format:",
                "delete <number>");
    }

    /**
     * Prints out error message if delete message is out of range.
     */
    private void deleteIndexErrorMessage() {
        printMessage("Ugh! The command should be in this format:",
                "delete <number>",
                "Note: number is based on the number from command 'list'");
    }

    /**
     * Prints out error message if event message does not contains /at.
     */
    private void eventErrorMessage() {
        printMessage("Ugh! The command should be in this format:",
               "event <description> /at <date/time>");
    }

    /**
     * Prints out error message if dateTime format is invalid.
     */
    private void dateTimeErrorMessage() {
        printMessage("Date/Time format is wrong. Ensure that it is in the this format:",
                "dd/mm/yy hhmm (24hrs format)");
    }

    /**
     * Formats the sentences that will be printed out by the chatbot.
     *
     * @param strings Arbitrary number of strings to be printed out
     */
    private void printMessage(String... strings) {
        System.out.println("\t____________________________________________________________");
        for (String str: strings)
            System.out.println("\t" + str);
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Main function to run the chatbot.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.chat();
    }
}
