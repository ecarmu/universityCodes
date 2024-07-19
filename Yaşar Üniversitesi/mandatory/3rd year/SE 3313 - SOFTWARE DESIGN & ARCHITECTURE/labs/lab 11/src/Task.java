public class Task {

    int id;

    public Task(int id, boolean isCompleted, String desc) {
        this.id = id;
        this.isCompleted = isCompleted;
        this.desc = desc;
    }

    boolean isCompleted;
    String desc;


    public String toString(){
        String st = "#" + id + " ";

        if (isCompleted)
            st += "[Done] ";
        else st += "[Waiting] ";

        st += desc;

        return st;

    }
}
