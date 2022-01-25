import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Duke {
    private Parser parser;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        parser = new Parser();
        try {
            storage = new Storage();
            taskList = storage.initialize();
        } catch (IOException e) {
            System.out.println(ui.getFileErrorMessage());
            taskList = new TaskList();
        }
    }

    public void run() {
        System.out.println(ui.getSetupMessage());
        while (true) {
            String line = ui.readLine();
            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                System.out.println(ui.printItems(taskList.getItems()));
            } else {
                try {
                    String command = parser.parseCommand(line);

                    if (command.equals("mark")) {
                        int index = parser.parseNumericalDescription(line, "mark", taskList.size());
                        System.out.println(taskList.markItemDone(index));
                        storage.modifyTask(index);
                    } else if (command.equals("unmark")) {
                        int index = parser.parseNumericalDescription(line, "unmark", taskList.size());
                        System.out.println(taskList.markItemUndone(index));
                        storage.modifyTask(index);
                    } else if (command.equals("delete")) {
                        int index = parser.parseNumericalDescription(line, "delete", taskList.size());
                        System.out.println(taskList.deleteItem(index));
                        storage.deleteTask(index);
                    } else {
                        // adding new tasks
                        if (command.equals("todo")) {
                            String description = parser.parseStringDescription(line, "todo");
                            taskList.addTask(new Todo(description));
                        } else if (command.equals("deadline")) {
                            String[] params = parser.parseFormatDescription(line, "deadline", "/by");
                            taskList.addTask(new Deadline(params[0], params[1]));
                        } else if (command.equals("event")) {
                            String[] params = parser.parseFormatDescription(line, "event", "/at");
                            taskList.addTask(new Event(params[0], params[1]));
                        }

                        Task latestTask = taskList.getLast();
                        storage.addTask(latestTask);
                        System.out.println(ui.getAddTaskMessage(latestTask, taskList.size()));
                    }
                } catch (WrongInputException | IncompleteInputException e) {
                    System.out.println(e.getMessage());
                } catch (DateTimeParseException e) {
                    System.out.println("Error parsing date, please enter dates in YYYY-MM-DD format!");
                } catch (IOException e) {
                    System.out.println(ui.getIoErrorMessage());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.exit(1);
                }
            }
        }
        // bye
        System.out.println(ui.getByeMessage());
        System.exit(0);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}