public class TravelFacade {
    ActivityBookingSystem activityBookingSystem;
    CarRentalSystem carRentalSystem;
    FlightBookingSystem flightBookingSystem;
    HotelReservationSystem hotelReservationSystem;
    InsuranceSystem insuranceSystem;
    NotificationService notificationService;

    public TravelFacade(ActivityBookingSystem activityBookingSystem, CarRentalSystem carRentalSystem, FlightBookingSystem flightBookingSystem, HotelReservationSystem hotelReservationSystem, InsuranceSystem insuranceSystem, NotificationService notificationService) {
        this.activityBookingSystem = activityBookingSystem;
        this.carRentalSystem = carRentalSystem;
        this.flightBookingSystem = flightBookingSystem;
        this.hotelReservationSystem = hotelReservationSystem;
        this.insuranceSystem = insuranceSystem;
        this.notificationService = notificationService;
    }

    public void plan_a_Trip(){
        flightBookingSystem.bookFlight();
        hotelReservationSystem.reserveHotel();
        carRentalSystem.rentCar();
        activityBookingSystem.bookActivity();
        insuranceSystem.purchaseInsurance();
        notificationService.notifyUser();
    }
}
