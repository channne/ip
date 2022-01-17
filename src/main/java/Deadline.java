public class Deadline extends Task {
    protected String time;

    public Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}
