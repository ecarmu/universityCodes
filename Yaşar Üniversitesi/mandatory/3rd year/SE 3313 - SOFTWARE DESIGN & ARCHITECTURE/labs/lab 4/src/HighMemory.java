public class HighMemory extends Resources{
    Server server;
    public HighMemory(Server server) {

        this.server = server;
    }

    public String getDescription(){
        return server.getDescription() + ", with High Memory";
    }

    @Override
    public double cost() {
        return  0.003067 + server.cost();
    }
}
