public class CargoCompA extends CargoCompany{

    public CargoCompA(DeliveryStrategy ds, PricingStrategy ps) {
        this.deliveryStrategy = ds;
        this.pricingStrategy = ps;
    }

    @Override
    public String toString() {

        return this.deliveryStrategy.printDeliveryType() + "\n"
                + this.deliveryStrategy.calculateDeliveryType() + " "
                + this.pricingStrategy.calculateDeliveryPrice();
    }
}
