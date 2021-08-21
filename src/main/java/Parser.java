/**
 * Deals with making sense of the user command.
 *
 * @author marcuspeh
 * @version A-MoreOOP
 * @since 21 Aug 2021
 */
public class Parser {
    /**
     * Static method to parses the user command and make sense of the command by the user.
     *
     * @param message command input by the user.
     * @return the type of command as a enum Keyword.
     * @throws DukeException if the command is not recognised.
     */
    public static Command parseChat(String message) throws DukeException {
        String command = message.split(" ")[0].toLowerCase();

        if (command.equals(Keyword.EXIT.getKeyword()))
            return null;
        else if (command.equals(Keyword.LIST.getKeyword()))
            return new ListCommand();
        else if (command.equals(Keyword.DONE.getKeyword()))
            return new DoneCommand(message);
        else if (command.equals(Keyword.DEADLINE.getKeyword()))
            return new DeadlineCommand(message);
        else if (command.equals(Keyword.EVENTS.getKeyword()))
            return new EventCommand(message);
        else if (command.equals(Keyword.TODOS.getKeyword()))
            return new TodoCommand(message);
        else if (command.equals(Keyword.DELETE.getKeyword()))
            return new DeleteCommand(message);

        throw new DukeException("Command not parsable");
    }
}
