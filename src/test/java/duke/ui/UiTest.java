package duke.ui;

import duke.command.TaskList;
import duke.task.Deadline;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void testGetAddTaskMessage() {
        assertEquals("added o.O:\n  [T][ ] this week iP\nNow there are 3 tasks on the list x)",
                new Ui().getAddTaskMessage(new Todo("this week iP"),3));
    }

    @Test
    public void printItems_emptyList_success() {
        assertEquals("There are no tasks on your list :O", new Ui().printItems(new ArrayList<>()));
    }

    @Test
    public void printItems_nonEmptyList_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Deadline("this week iP", "2022-01-27"));
        taskList.addTask(new Todo("write junit tests"));
        assertEquals("Here are the tasks on your list :O\n" +
                "1. [D][ ] this week iP (by: Jan 27 2022)\n" +
                "2. [T][ ] write junit tests", new Ui().printItems(taskList.getItems()));
    }
}
