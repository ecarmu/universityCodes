public class BasicCPU extends Server{
    public BasicCPU() {
        description = "Normal CPU";
    }

    public String getDescription(){
        return description;
    }

    @Override
    public double cost() {
        return 0.01484;
    }
}
