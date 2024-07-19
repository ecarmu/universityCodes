public class InsuranceSystem {

    String cityName;
    String dateStart;
    String dateEnd;

    public InsuranceSystem(String cityName, String dateStart, String dateEnd) {
        this.cityName = cityName;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }



    public void purchaseInsurance(){
        System.out.println("Insurance purchased for the trip to " + cityName + " from " + dateStart + " to " + dateEnd);
    }
}
