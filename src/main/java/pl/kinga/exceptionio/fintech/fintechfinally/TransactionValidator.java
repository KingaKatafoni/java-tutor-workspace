package pl.kinga.exceptionio.fintech.fintechfinally;

public class TransactionValidator {
    public static void validateAndProcess(String amountStr, String accountId){

        try {
            double amount = Double.parseDouble(amountStr);
            if (accountId == null) {
                throw new NullPointerException("");
            }
            if (amount < 0) {
                throw new IllegalArgumentException("Amount must be positive");
            }
            System.out.println("Transaction processed: " + amount + " for account " + accountId);

        } catch (NumberFormatException e) {
            System.out.println("Invalid amount format: " + amountStr);
        } catch (NullPointerException e){
            System.out.println("Account ID is null");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation error: " + e.getMessage());
        } finally {
            System.out.println("--- Validation complete ---");
        }
    }
}
