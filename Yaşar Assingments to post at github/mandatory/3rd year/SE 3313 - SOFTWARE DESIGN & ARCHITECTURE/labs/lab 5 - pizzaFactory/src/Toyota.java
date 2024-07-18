public class Toyota extends CarBrand{
    @Override
    public Car createCar(String type) {

        Car car = null;

        switch (type){
            case "sedan":
                car = new ToyotaStyleSedan();
                break;
            case "hatchback":
                car = new ToyotaStyleHatchback();
                break;
            case "SUV":
                car = new ToyotaStyleSUV();
                break;

        }

        return car;
    }
}
