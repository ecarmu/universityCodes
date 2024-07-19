public class Elevator {
    ElevatorState up;
    ElevatorState stay;
    ElevatorState down;
    ElevatorState state;

    int currentFloor;

    public Elevator(int currentFloor) {
        this.up = new MovingUpState(this);
        this.stay = new StationaryState(this);
        this.down = new MovingDownState(this);
        this.state = stay;
        this.currentFloor = currentFloor;
    }

    public void pressButton(int destFloor){
        state.pressButton(destFloor);
    }


    public ElevatorState getState() {
        return state;
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }



}
