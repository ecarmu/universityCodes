public class HotelReservationSystem {

    String hotelName;
    String dateStart;
    String dateEnd;

    public HotelReservationSystem(String hotelName, String dateStart, String dateEnd) {
        this.hotelName = hotelName;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }



    public void reserveHotel(){
        System.out.println("Hotel reserved in " + hotelName + " from " + dateStart + " to " + dateEnd);
    }
}
