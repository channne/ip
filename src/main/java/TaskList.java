import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list;

    protected TaskList() {
        list = new ArrayList<>();
    }

    public void addTask(Task t) {
        list.add(t);
    }

    protected Task getLast() {
        return list.get(list.size() - 1);
    }

    protected int size() {
        return list.size();
    }

    public String markItemDone(int index) throws InvalidActionException {
        Task t = list.get(index);
        t.markAsDone();
        return "Task done! \\(n_n)/\n " + t.toString();
    }

    public String markItemUndone(int index) throws InvalidActionException {
        Task t = list.get(index);
        t.markUndone();
        return "Task not done =(\n " + t.toString();
    }

    public String deleteItem(int index) {
        Task t = list.remove(index);
        return "deleted this item O_O:\n  " + t + "\nNow there are " + list.size() + " tasks on the list x)";
    }

    protected ArrayList<Task> getItems() {
        return list;
    }
}