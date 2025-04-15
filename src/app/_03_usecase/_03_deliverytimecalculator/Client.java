package app._03_usecase._03_deliverytimecalculator;

public class Client {
    public static void main(String[] args)throws Exception {
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.loadConfigurations();
        deliverySystem.run();

    }
}
