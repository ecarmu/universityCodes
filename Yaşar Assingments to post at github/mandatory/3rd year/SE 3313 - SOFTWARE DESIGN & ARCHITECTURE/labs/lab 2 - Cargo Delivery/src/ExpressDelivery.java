public class ExpressDelivery implements DeliveryStrategy{
    @Override
    public String printDeliveryType() {
        return "Delivery type is EXPRESS DELIVERY";
    }

    @Override
    public int calculateDeliveryType() {
        return 3;
    }
}
