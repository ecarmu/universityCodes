public class LowMemory extends Resources{

    Server server;

    public LowMemory(Server server) {

        this.server = server;
    }

    public String getDescription(){
        return server.getDescription() + ", with Low Memory";

    }

    @Override
    public double cost() {
        return 0.000920 + server.cost();

    }
}
