public class Circle extends TwoDimShapes implements TwoDimCalculatable{

    double radius;

    public Circle(double radius) {
        super("Circle");
        this.radius = radius;
    }

    /*

    @Override
    public double calculateArea() {
        return Math.PI*radius*radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2*Math.PI*radius;
    }*/

    @Override
    public String toString() {
        String st = "TwoDimShape{shapeType = 2d} " +  nameOfShapes + "{radius = " + radius + ", perimeter = " + calculatePerimeter() + ", Area = " + calculateArea() + "}";
        return st;
    }

    @Override
    public double calculateArea() {
        return Math.PI*radius*radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2*Math.PI*radius;
    }
}
