package pl.kinga.testowanie.lekcja7_6;

public interface NotificationService {
    void sendApprovalNotification(String holderName, String policyId);
    void sendRejectionNotification(String holderName, String policyId, String reason);
}
