public class HighEndCPU extends Server{

    public HighEndCPU() {
        description = "High-End CPU";
    }

    public String getDescription(){
        return description;
    }

    @Override
    public double cost() {
        return 0.049468;
    }
}
