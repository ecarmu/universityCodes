import java.util.Scanner;

public class Main {

    CarBrand carBrand;

    public Main(CarBrand carBrand){
        this.carBrand = carBrand;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String st = scanner.nextLine();

        if(st.equals("Toyota")){
            Toyota toyota = new Toyota();

            Scanner scanner2 = new Scanner(System.in);
            String st2 = scanner2.nextLine();


            if(st2.equals("SUV") || st2.equals("sedan") || st2.equals("hatchback")){
                toyota.orderCar(st2);
            }
        }

        else if (st.equals("Mercedes")){
            Mercedes mercedes = new Mercedes();

            Scanner scanner2 = new Scanner(System.in);
            String st2 = scanner2.nextLine();

            if(st2.equals("SUV") || st2.equals("sedan") || st2.equals("hatchback")){
                mercedes.orderCar(st2);
            }
        }

        else if(st.equals("Renault")) {
            Renault renault = new Renault();

            Scanner scanner2 = new Scanner(System.in);
            String st2 = scanner2.nextLine();

            if (st2.equals("SUV") || st2.equals("sedan") || st2.equals("hatchback")) {
                renault.orderCar(st2);
            }
        }


    }
}