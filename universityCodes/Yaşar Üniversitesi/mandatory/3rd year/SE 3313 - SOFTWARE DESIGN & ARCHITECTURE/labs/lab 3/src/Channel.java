import java.util.ArrayList;

public class Channel implements Subject {

    public String channelName;

    public ArrayList<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int index = observers.indexOf(observer);

        if(index > 0)
        observers.remove(index);
    }

    public Channel(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public void notifyObserver() {

        for (Observer observer: observers) {
            observer.update(channelName);
        }
    }
}
