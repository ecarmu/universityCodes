public class Mercedes extends CarBrand{

    @Override
    public Car createCar(String type) {

        Car car = null;

        switch (type){
            case "sedan":
                car = new MercedesStyleSedan();
                break;
            case "hatchback":
                car = new MercedesStyleHatchback();
                break;
            case "SUV":
                car = new MercedesStyleSUV();
                break;

        }

        return car;
    }
}
