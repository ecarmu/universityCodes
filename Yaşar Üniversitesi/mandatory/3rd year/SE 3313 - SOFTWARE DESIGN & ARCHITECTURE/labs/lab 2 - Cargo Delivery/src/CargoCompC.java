public class CargoCompC extends CargoCompany{
    public CargoCompC(ExpressDelivery expressDelivery, FlatRatePricing flatRatePricing) {
        this.deliveryStrategy = expressDelivery;
        this.pricingStrategy = flatRatePricing;
    }

    @Override
    public String toString() {
        return this.deliveryStrategy.printDeliveryType() + "\n"
                + this.deliveryStrategy.calculateDeliveryType() + " "
                + this.pricingStrategy.calculateDeliveryPrice();
    }
}
