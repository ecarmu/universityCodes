package week2;

import java.util.Scanner;

public class TriangleChecker {
    public static final int triangleCornerCount = 3;
    public static int[] arr = new int[triangleCornerCount];
    static boolean isValidTriangle;

    public static void getNumbers(){
        int i = 0;

        Scanner scanner = new Scanner(System.in);

        while(i<3){
            try{
                System.out.println(i+1 + ". sayıyı girin: ");
                arr[i] = scanner.nextInt();
                i++;
            }
            catch (Exception e){
                System.out.println("Invalid thing entered. Try to enter again");
                scanner.nextLine();
            }

        }

    }

    public static boolean isEquilateralTriangle(){
        return (arr[0] == arr[1]) && (arr[1] == arr[2]);
    }

    public static boolean isIsosceles(){
        return (arr[0] == arr[1]) || (arr[1] == arr[2]) || (arr[0] == arr[2]);
    }

    public static int abs(int a){
        if(a>=0)
            return a;
        else return -1*a;
    }

    public static boolean validTriangleChecker(){
        return isValidTriangle = ( abs(arr[0] - arr[2]) < arr[1]) && ( arr[1] < abs(arr[0] + arr[2]) );
    }

    public static void triangleResultShower(){
        if(isValidTriangle){
            if (isEquilateralTriangle()) {
                System.out.println("Equilateral (eşkenar) triangle");
            }
            else if (isIsosceles()){
                System.out.println("Isoceles (ikizkenar) triangle");
            }
            else{
                System.out.println("Scalene (çeşitkenar) triangle");
            }
        }
        else{
            System.out.println("Not a triangle");
        }
    }
}
