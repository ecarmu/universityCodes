public class BasicGPU extends Resources{

    Server server;
    public BasicGPU(Server server) {

        this.server = server;
    }


    public String getDescription(){
        return server.getDescription() + " Basic GPU";
    }

    @Override
    public double cost() {
        return 0.006867 + server.cost();
    }
}
