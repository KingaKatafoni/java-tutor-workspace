package pl.kinga.funkcyjnajava.lekcja5_5.logistics;

import java.math.BigDecimal;

public record CourierPackage(String packageId, String senderName, String receiverName, String receiverCity,
                             double weight, BigDecimal declaredValue, String status) {
}
