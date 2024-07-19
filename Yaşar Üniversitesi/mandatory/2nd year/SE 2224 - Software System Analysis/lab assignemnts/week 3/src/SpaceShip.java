public interface SpaceShip {

    public boolean launch();
    public boolean land();
    public boolean canCarry(Item item);
    public double carry(Item item);

}
