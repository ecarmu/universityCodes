import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Data Center Configuration To1ol!");
        System.out.println("Select Server Type:\n1. High End CPU\n2. Normal CPU");
        Scanner myObj = new Scanner(System.in);

        String selection = myObj.nextLine();
        Server server_selected = null;

        switch (selection) {
            case "1" :
                server_selected = new HighEndCPU();
                break;
            case "2" :
                server_selected = new BasicCPU();
        }

        System.out.println("Select Memory Type:\n1. High Memory\n2. Low Memory");

        selection = myObj.nextLine();

        switch (selection) {
            case "1" :
                server_selected = new HighMemory(server_selected);
                break;
            case "2" :
                server_selected = new LowMemory(server_selected);
        }


        System.out.println("Select GPU Type:\n1. High End GPU\n2. Basic GPU");

        selection = myObj.nextLine();

        switch (selection) {
            case "1" :
                server_selected = new HighEndGPU(server_selected);
                break;
            case "2" :
                server_selected = new BasicGPU(server_selected);
        }

        System.out.println("Total Price: " + server_selected.cost());
        System.out.println("Descrption: Server with " + server_selected.getDescription() );




    }
}