package pl.kinga.exceptionio.fintech.fitechgoodpractices;

public class Main {
    public static void main(String[] args){
        AccountService accountService = new AccountService();
        try {
            accountService.withdraw("ACC-001", 200.00);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        try {
            accountService.withdraw("09", 200.00);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        try {
            accountService.withdraw("ACC-001", -30.00);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        try {
            accountService.withdraw("ACC-002", 350.00);
        } catch (InsufficientFundsException e){
            System.out.println("No founds on account: " + e.getAccountId());
        }
    }
}
