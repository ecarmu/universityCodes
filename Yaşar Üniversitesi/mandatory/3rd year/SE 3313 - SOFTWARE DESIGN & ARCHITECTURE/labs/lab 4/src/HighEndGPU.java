public class HighEndGPU extends Resources{

    Server server;

    public HighEndGPU(Server server) {
        this.server = server;
    }


    public String getDescription(){
        return server.getDescription() + " , with High-end GPU";
    }

    @Override
    public double cost() {
        return 0.022890 + server.cost();
    }
}
