public class StandartDelivery implements DeliveryStrategy{
    @Override
    public String printDeliveryType() {
        return "Delivery type is STANDART DELIVERY";
    }

    @Override
    public int calculateDeliveryType() {
        return 7;
    }
}
