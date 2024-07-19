public class Sphere extends ThreeDimShapes implements ThreeDimCalculatable, TwoDimCalculatable{
    double radius;

    public Sphere(double radius) {
        super("Sphere");
        this.radius = radius;
    }

    @Override
    public String toString() {
        String st = "ThreeDimShape{shapeType = 3d} " +  nameOfShapes + "{radius = " + radius + ", volume = " + calculateVolume() +
                ", area = " +  calculateArea()
                + ", perimeter = " + calculatePerimeter() +  "}";
        return st;
    }

    @Override
    public double calculateVolume() {
        return 4* Math.PI*radius*radius*radius/3;
    }

    @Override
    public double calculateArea() {
        return 4* Math.PI*radius*radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2* Math.PI * radius;
    }
}
