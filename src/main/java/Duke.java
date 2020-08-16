import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Duke {



    public static void main(String[] args) {

        System.out.println("Hello from Bikini Bottom!");
        System.out.println("____________________________________________________________\n"
            + "Hello! I'm Spongebob\n"
            + "What can I do for you?\n"
            + "____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        ArrayList<Task> lstOfTasks = new ArrayList<>();


        while (!str.equals("bye")) {
            System.out.println("____________________________________________________________\n");
            try {

                if (str.equals("list")) {
                    int counter = 1;
                    System.out.println("Here are the tasks in your list: \n");
                    for (int i = 0; i < lstOfTasks.size(); i++) {
                        if (lstOfTasks.get(i) != null) {
                            System.out.println(counter + ". " + lstOfTasks.get(i));
                            counter++;
                        } else {
                            break;
                        }
                    }
                } else if (str.startsWith("delete ")) {
                    int temp = Integer.parseInt(str.substring(5));
                    lstOfTasks.get(temp - 1).doTask();

                } else if (str.startsWith("done ")) {
                    int temp = Integer.parseInt(str.substring(5));
                    lstOfTasks.get(temp-1).doTask();
                } else if (str.startsWith("todo ")) {
                    if (str.length() <= 5) {
                        throw new DescriptionException("todo");
                    }
                    str = str.substring(5);
                    lstOfTasks.add(new ToDo(str));

                    System.out.println("Got it. I've added this task: \n"
                        + lstOfTasks.get(lstOfTasks.size()-1)
                        + "\nNow you have " + lstOfTasks.size() + " task(s) in the list.");

                } else if (str.startsWith("deadline ")) {

                    if (str.length() <= 9) {
                        throw new DescriptionException("deadline");
                    }
                    str = str.substring(9);
                    if (!str.contains(" /by ")) {
                        throw new TrackingException("deadline");
                    }
                    String[] temp = str.split(" /by ");
                    String desc = temp[0];
                    String deadline = temp[1];
                    if (desc.length() == 0) {
                        throw new DescriptionException("deadline");
                    }
                    if (deadline.length() == 0) {
                        throw new TrackingException("deadline");
                    }

                    String by = temp[1];
                    lstOfTasks.add(new Deadline(desc, by));

                    System.out.println("Got it. I've added this task: \n"
                        + lstOfTasks.get(lstOfTasks.size() - 1)
                        + "\nNow you have " + lstOfTasks.size() + " task(s) in the list.");

                } else if (str.startsWith("event ")) {
                    if (str.length() <= 6) {
                        throw new DescriptionException("event");
                    }
                    str = str.substring(6);
                    if (!str.contains(" /at ")) {
                        throw new TrackingException("event");
                    }
                    String[] temp = str.split(" /at ");
                    String desc = temp[0];
                    String at = temp[1];
                    if (desc.length() == 0) {
                        throw new DescriptionException("event");
                    }
                    if (at.length() == 0) {
                        throw new TrackingException("event");
                    }
                    lstOfTasks.add(new Events(desc, at));

                    System.out.println("Got it. I've added this task: \n"
                        + lstOfTasks.get(lstOfTasks.size() - 1)
                        + "\nNow you have " + lstOfTasks.size() + " task(s) in the list.");

                } else if (str.equals("event") || str.equals("deadline") || str.equals("todo") ||
                str.equals("done")) {
                    throw new DescriptionException(str);
                } else {
                    throw new CommandException(str);
                }
            } catch (DukeException ex) {
                System.out.println(ex);
            }

            System.out.println("____________________________________________________________\n");

            str = sc.nextLine();

        }

        System.out.println("____________________________________________________________\n"
            + "Bye. Hope to see you again soon! Bahahahaha!\n"
            + "____________________________________________________________\n");



    }
}
