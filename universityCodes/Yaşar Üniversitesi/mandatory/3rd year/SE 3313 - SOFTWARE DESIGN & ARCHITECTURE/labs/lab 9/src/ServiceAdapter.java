public class ServiceAdapter implements FileReaderr{
    Service s;

    public ServiceAdapter(Service service){
        this.s = service;
    }
    @Override
    public void readFile(String path) {
        s.readDataFile(path);
    }
}
