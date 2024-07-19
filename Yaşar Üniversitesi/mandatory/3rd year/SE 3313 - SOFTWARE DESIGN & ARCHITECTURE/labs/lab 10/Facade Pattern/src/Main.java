public class Main {

    public static void main(String[] args) {
        String arrivalDate = "2023-12-11";
        String sightSeeingDate = "2023-12-15";
        String departureDate = "2023-12-17";
        String cityName = "Istanbul";

        ActivityBookingSystem activityBookingSystem = new ActivityBookingSystem(cityName, sightSeeingDate, "Sightseeing Tour");
        CarRentalSystem carRentalSystem = new CarRentalSystem(cityName, arrivalDate, 6);
        FlightBookingSystem flightBookingSystem= new FlightBookingSystem("Izmir", cityName, arrivalDate);
        HotelReservationSystem hotelReservationSystem = new HotelReservationSystem("Istanbul Hotel", arrivalDate, departureDate);
        InsuranceSystem insuranceSystem = new InsuranceSystem(cityName, arrivalDate, departureDate);
        NotificationService notificationService = new NotificationService();

        TravelFacade travelFacade = new TravelFacade(
                activityBookingSystem,
                carRentalSystem,
                flightBookingSystem,
                hotelReservationSystem,
                insuranceSystem,
                notificationService
        );

        travelFacade.plan_a_Trip();
    }
}
