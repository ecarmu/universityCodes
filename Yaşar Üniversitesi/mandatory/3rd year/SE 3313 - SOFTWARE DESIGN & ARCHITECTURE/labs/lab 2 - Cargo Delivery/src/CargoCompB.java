public class CargoCompB extends CargoCompany{
    public CargoCompB(StandartDelivery standartDelivery, VariableRatePricing variableRatePricing) {
        this.deliveryStrategy = standartDelivery;
        this.pricingStrategy = variableRatePricing;
    }

    @Override
    public String toString() {

        return this.deliveryStrategy.printDeliveryType() + "\n"
                + this.deliveryStrategy.calculateDeliveryType() + " "
                + this.pricingStrategy.calculateDeliveryPrice();
    }
}
