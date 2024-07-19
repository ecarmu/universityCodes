import java.util.ArrayList;

/*
Create an abstract class called Shape, it should have:
name(string) and abstract toString function.

Create an interfaces called PerimeterCalculation, it should have:
calculatePerimeter() : returns the perimeter of the shape as double

Create an interfaces called AreaCalculation, it should have:
calculateArea() : returns the area of the shape as double

Create an interfaces called VolumeCalculation, it should have:
calculateVolume() : returns the volume of the shape as double

Create an abstract class called TwoDimShapes, it extends Shape and implements Perimeter and Area calculations

Create an abstract class called ThreeDimShapes, it extends Shape and implements Area and Volume calculations.

Create and implement Circle, Square, Cone, and Cube classes with necessary functions and inheritance.

Note: You can use Math library and look up formulas of shapes.

 */
public class Main {

    public static void main(String[] args) {
        ArrayList<Shape> shapes = new ArrayList<>();
        ArrayList<TwoDimShapes> twoDimentionalShapes = new ArrayList<>();
        ArrayList<ThreeDimShapes> threeDimentionalShapes = new ArrayList<>();

        TwoDimShapes rectangle = new Rectangle(5, 10);
        TwoDimShapes circle = new Circle(5);
        ThreeDimShapes cube = new Cube(5);
        ThreeDimShapes sphere = new Sphere(5);

        System.out.println(cube.toString());
        System.out.println(rectangle.toString());
        System.out.println(sphere.toString());
        System.out.println(circle.toString());



    }
}
