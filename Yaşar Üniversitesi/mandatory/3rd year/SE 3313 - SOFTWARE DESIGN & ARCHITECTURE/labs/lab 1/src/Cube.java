public class Cube extends ThreeDimShapes implements ThreeDimCalculatable{

    double edge;

    public Cube(double edge) {
        super("Cube");
        this.edge = edge;
    }


    @Override
    public String toString() {
        String st = "ThreeDimShape{shapeType = 3d} " +  nameOfShapes + "{edge = " + edge + ", volume = " + calculateVolume() + "}";
        return st;
    }


    @Override
    public double calculateVolume() {
        return edge*edge*edge;
    }
}
