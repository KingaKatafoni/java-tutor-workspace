package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_2;

public record Order(String orderId, String customerEmail, double totalPrice, String shippingMethod) {
}
