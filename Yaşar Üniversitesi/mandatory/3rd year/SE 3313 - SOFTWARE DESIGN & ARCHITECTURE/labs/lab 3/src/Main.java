public class Main {
    public static void main(String[] args) {
        Channel veritasum = new Channel("Veritasum");
        Channel minutePhysics = new Channel("Minute Physics");

        User elena = new User("Elena", MemberShip.NORMAL);
        User derek = new User("Derek", MemberShip.NORMAL);
        User gale = new User("Gale", MemberShip.PREMUIM);
        User lane = new User("Lane", MemberShip.PREMUIM);

        veritasum.registerObserver(elena);
        veritasum.registerObserver(derek);
        minutePhysics.registerObserver(gale);
        minutePhysics.registerObserver(lane);

        veritasum.notifyObserver();
        minutePhysics.notifyObserver();
    }
}