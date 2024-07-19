public class Main {
    public static void main(String[] args) {


        TaskView view = new TaskView();
        TaskModel model = new TaskModel();
        TaskController controller = new TaskController(model, view);

        while(model.isRunning){
            view.showOptionsToUser();
            int selection = view.getSelectedOption();
            controller.Control(selection);

        }

    }
}