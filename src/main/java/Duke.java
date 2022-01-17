import java.util.*;

public class Duke {
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm \n" + logo);
        System.out.println("What can I do for you? =)");

        while (true) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                break;
            }
            Duke.echo(s);
        }

        System.out.println("Bye t_t");
    }

    public static void echo(String s) {
        System.out.println("\t" + s);
    }
}
