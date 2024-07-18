public class Main {
    public static void main(String[] args) {
        CargoCompany compA = new CargoCompA(new ExpressDelivery(), new VariableRatePricing());
        CargoCompany compB = new CargoCompB(new StandartDelivery(), new VariableRatePricing());
        CargoCompany compC = new CargoCompC(new ExpressDelivery(), new FlatRatePricing());

        System.out.println("----- Strategies for COMPANY A -----");
        System.out.println(compA);


        System.out.println("----- Strategies for COMPANY B ----- \n");
        System.out.println(compB);


        System.out.println("----- Strategies for COMPANY C ----- \n");
        System.out.println(compC);


    }
}