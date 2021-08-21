import java.text.ParseException;

/**
 * Class to store event. Subclass of task.
 *
 * @author marcuspeh
 * @version A-MoreOOP
 * @since 21 Aug 2021
 */
public class Events extends Task {
    /**
     * Constructor for Events.
     *
     * @param task task to be stored.
     * @param dateTime dateTime for the task.
     * @throws ParseException Date / Time format is invalid.
     */
    Events(String task, String dateTime) throws ParseException {
        super(task, dateTime);
    }

    /**
     * Constructor for Events.
     *
     * @param task task to be stored.
     * @param dateTime dateTime for the task.
     * @param done whether the task is done.
     */
    Events(String task, String dateTime, boolean done) throws ParseException {
        super(task, dateTime, done);
    }


    /**
     * To save the task to the txt file.
     * Format is as follow: <Type(E)> | <Description> | <Done> | <DateTime>.
     *
     * @return string to save the txt file.
     */
    public String saveOutput() {
        return String.format("D | %s | %s | %s", super.getTask(),
                super.getIsDone() ? 1 : 0, getDateTime());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + super.getDateTime() + ")";
    }
}
