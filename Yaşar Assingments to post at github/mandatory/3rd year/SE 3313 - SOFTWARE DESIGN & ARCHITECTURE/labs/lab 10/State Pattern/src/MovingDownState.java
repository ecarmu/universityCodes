public class MovingDownState implements ElevatorState{
    Elevator e;

    public MovingDownState(Elevator elevator) {
        this.e = elevator;
    }

    @Override
    public void pressButton(int destFloor) {

        if(e.currentFloor < destFloor){
            e.setState(e.up);
            System.out.println("Elevator is moving up to floor " + destFloor);
        }

        else if(e.currentFloor == destFloor){
            e.setState(e.stay);
            System.out.println("Elevator is stationary at" + destFloor);
        }

        else{
            System.out.println("Elevator is moving down to floor " + destFloor);
        }
    }
}
