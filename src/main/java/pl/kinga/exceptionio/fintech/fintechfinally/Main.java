package pl.kinga.exceptionio.fintech.fintechfinally;

public class Main {
    public static void main(String[] args) {
        System.out.println("---------1---------");
        TransactionValidator.validateAndProcess("100.50", "ACC-001");  // ok
        System.out.println("---------2---------");
        TransactionValidator.validateAndProcess("abc", "ACC-002");     // NumberFormatException
        System.out.println("---------3---------");
        TransactionValidator.validateAndProcess("50.00", null);        // NullPointerException
        System.out.println("---------4---------");
        TransactionValidator.validateAndProcess("-30.00", "ACC-003"); // IllegalArgumentException
    }
}
