public class Rectangle extends TwoDimShapes implements TwoDimCalculatable{

    double height, width;

    public Rectangle(double height, double width) {
        super("Rectangle");
        this.height = height;
        this.width = width;

    }

    @Override
    public String toString() {
        String st = "TwoDimShape{shapeType = 2d} " +  nameOfShapes + "{height = " + height + ", width = " + width + ", perimeter = " + calculatePerimeter() + ", Area = " + calculateArea() + "}";
        return st;
    }

    @Override
    public double calculateArea() {
        return height * width;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (height + width);
    }
}
