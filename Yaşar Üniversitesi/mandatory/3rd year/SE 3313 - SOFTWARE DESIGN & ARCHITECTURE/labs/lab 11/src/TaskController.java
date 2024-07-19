import java.util.Objects;
import java.util.Scanner;

public class TaskController {
    TaskModel model;
    TaskView view;

    public TaskController(TaskModel model, TaskView view) {
        this.model = model;
        this.view = view;
    }

    public void Control(int selection){

        switch (selection) {
            case 1 -> addTask();
            case 2 -> viewTasks();
            case 3 -> markTaskAsComplete();
            case 4 -> exit();
        }
    }



    public void addTask(){
        System.out.print("Enter desc of the task: ");
        Scanner sc =new Scanner(System.in);
        String desc = sc.nextLine();

        System.out.print("\nIs task completed? (y/n)");
        Scanner sc2 =new Scanner(System.in);
        String isCompleted = sc.nextLine();

        boolean isComplete = false;

        if(Objects.equals(isCompleted, "y"))
            isComplete = true;
        else if(Objects.equals(isCompleted, "n"))
            isComplete = false;


        int id = model.tasks.size();

        model.tasks.add(new Task(id, isComplete, desc));
    }

    public void viewTasks(){
        for (Task t :
                model.tasks) {
            System.out.println(t);
        }
    }

    public void markTaskAsComplete(){
        System.out.print("Enter id of the task that to mark as complete: ");
        Scanner sc =new Scanner(System.in);
        int selection = sc.nextInt();
        model.tasks.get(selection).isCompleted = true;
    }

    public void exit(){
        model.isRunning = false;
    }


}
