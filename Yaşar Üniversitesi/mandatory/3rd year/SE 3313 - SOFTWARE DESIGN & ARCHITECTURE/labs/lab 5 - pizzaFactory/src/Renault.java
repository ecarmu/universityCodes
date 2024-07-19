public class Renault extends CarBrand{
    @Override
    public Car createCar(String type) {

        Car car = null;

        switch (type){
            case "sedan":
                car = new RenaultStyleSedan();
                break;
            case "hatchback":
                car = new RenaultStyleHatchback();
                break;
            case "SUV":
                car = new RenaultStyleSUV();
                break;

        }

        return car;
    }
}
