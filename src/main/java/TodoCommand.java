public class TodoCommand implements Command {
    /** Stores the message entered by the user. */
    private String message;

    /** Constructor for TodoCommand.
     *
     * @param message Stores the message entered by the user.
     */
    TodoCommand(String message) {
        this.message = message;
    }

    /**
     * Main codes to run for the chat.
     *
     * @param taskList TaskList to execute the command.
     * @param ui To interact with the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addTodo(message);
    }
}
