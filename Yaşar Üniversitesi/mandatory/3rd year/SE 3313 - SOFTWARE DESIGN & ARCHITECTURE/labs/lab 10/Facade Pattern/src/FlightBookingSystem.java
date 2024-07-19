public class FlightBookingSystem {

    String depCity;
    String destCity;
    String date;

    FlightBookingSystem(String depCity, String destCity, String date){
        this.depCity = depCity;
        this.destCity = destCity;
        this.date = date;
    }

    public void bookFlight(){
        System.out.println("Flight booked from " + depCity + " to " + destCity + " on " + date);
    }


}
