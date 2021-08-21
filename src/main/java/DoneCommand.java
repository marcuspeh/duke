public class DoneCommand implements Command {
    /** Stores the message entered by the user. */
    private String message;

    /** Constructor for DoneCommand.
     *
     * @param message Stores the message entered by the user.
     */
    DoneCommand(String message) {
        this.message = message;
    }

    /**
     * Main codes to run for the chat.
     *
     * @param taskList TaskList to execute the command.
     * @param ui       To interact with the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        try {
            taskList.markDone(Integer.parseInt(message.substring(Keyword.DONE.length() + 1)));
        } catch (NumberFormatException e) {
            ui.doneErrorMessage();
        } catch (IndexOutOfBoundsException e) {
            ui.doneIndexErrorMessage();
        }
    }
}
