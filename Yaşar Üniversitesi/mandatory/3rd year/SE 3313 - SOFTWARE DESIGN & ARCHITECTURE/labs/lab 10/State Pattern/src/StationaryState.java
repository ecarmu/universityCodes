public class StationaryState implements ElevatorState{

    Elevator e;

    public StationaryState(Elevator elevator) {
        this.e = elevator;
    }


    @Override
    public void pressButton(int destFloor) {
        if(e.currentFloor < destFloor){
            e.setState(e.up);
            System.out.println("Elevator is moving up to floor " + destFloor);
        }

        else if(e.currentFloor > destFloor){
            e.setState(e.down);
            System.out.println("Elevator is down to floor " + destFloor);
        }

        else{
            System.out.println("Elevator is stationary at " + destFloor);
        }
    }
}
