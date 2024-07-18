public class ActivityBookingSystem {
    String destCity;
    String date;
    String activityName;

    public ActivityBookingSystem(String destCity, String date, String activityName) {
        this.destCity = destCity;
        this.date = date;
        this.activityName = activityName;
    }

    public void bookActivity(){
        System.out.println("Activity booked in " + destCity + ": " + activityName + " on " + date);
    }
}
