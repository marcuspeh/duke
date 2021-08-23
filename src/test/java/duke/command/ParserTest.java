package duke.command;


import duke.util.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseChat_lowercaseInput_success() {
        try {
            Command command = Parser.parseChat("bye");
            assertNull(command, "Exit command should not return anything.");
        } catch (DukeException e) {
            fail();
        }

        try {
            boolean isInstant = Parser.parseChat("list") instanceof ListCommand;
            assertTrue(isInstant, "List command return is returned.");
        } catch (DukeException e) {
            fail();
        }

        try {
            boolean isInstant = Parser.parseChat("done 2") instanceof DoneCommand;
            assertTrue(isInstant, "Done command return is returned.");
        } catch (DukeException e) {
            fail();
        }

        try {
            boolean isInstant = Parser.parseChat("deadline cs2106") instanceof DeadlineCommand;
            assertTrue(isInstant, "Deadline command return is returned.");
        } catch (DukeException e) {
            fail();
        }

        try {
            boolean isInstant = Parser.parseChat("event cs2106") instanceof EventCommand;
            assertTrue(isInstant, "Event command return is returned.");
        } catch (DukeException e) {
            fail();
        }

        try {
            boolean isInstant = Parser.parseChat("todo cs2106") instanceof TodoCommand;
            assertTrue(isInstant, "Todo command return is returned.");
        } catch (DukeException e) {
            fail();
        }

        try {
            boolean isInstant = Parser.parseChat("delete 3") instanceof DeleteCommand;
            assertTrue(isInstant, "Delete command return is returned.");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseChat_uppercaseInput_success() {
        try {
            Command command = Parser.parseChat("BYE");
            assertNull(command, "Exit command should not return anything.");
        } catch (DukeException e) {
            fail();
        }

        try {
            boolean isInstant = Parser.parseChat("LIST") instanceof ListCommand;
            assertTrue(isInstant, "List command return is returned.");
        } catch (DukeException e) {
            fail();
        }

        try {
            boolean isInstant = Parser.parseChat("DONE 2") instanceof DoneCommand;
            assertTrue(isInstant, "Done command return is returned.");
        } catch (DukeException e) {
            fail();
        }

        try {
            boolean isInstant = Parser.parseChat("DEADLINE cs2106") instanceof DeadlineCommand;
            assertTrue(isInstant, "Deadline command return is returned.");
        } catch (DukeException e) {
            fail();
        }

        try {
            boolean isInstant = Parser.parseChat("EVENT cs2106") instanceof EventCommand;
            assertTrue(isInstant, "Event command return is returned.");
        } catch (DukeException e) {
            fail();
        }

        try {
            boolean isInstant = Parser.parseChat("TODO cs2106") instanceof TodoCommand;
            assertTrue(isInstant, "Todo command return is returned.");
        } catch (DukeException e) {
            fail();
        }

        try {
            boolean isInstant = Parser.parseChat("DELETE 3") instanceof DeleteCommand;
            assertTrue(isInstant, "Delete command return is returned.");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseChat_incorrectInput_exceptionThrown() {
        try {
            Parser.parseChat("hello world.");
            fail();
        } catch (DukeException e) {
            assertEquals("Command not parsable", e.getMessage());
        }
    }
}