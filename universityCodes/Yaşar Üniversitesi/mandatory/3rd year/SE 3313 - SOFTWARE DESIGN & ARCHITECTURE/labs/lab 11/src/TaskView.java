import java.util.Scanner;

public class TaskView {
    public void showOptionsToUser() {
        System.out.println("Options:\n" +
                "1- Add Task\n" +
                "2- View Tasks\n" +
                "3- Mark Task as Complete\n" +
                "4- Exit");
    }

    public int getSelectedOption() {
        System.out.print("Enter your choice: ");
        Scanner sc =new Scanner(System.in);
        int selection = sc.nextInt();
        return selection;
    }

}
