package pl.kinga.kolekcjegeneryki.finances;

public class Main {
    public static void main(String[] args){
        BankDataManager bankDataManager = new BankDataManager();

        bankDataManager.addAccount("839393948290202", "Krzysztof Wozniak");
        bankDataManager.addAccount("364527290201737", "Monika Panik");
        bankDataManager.addAccount("383746292902734", "Zygmunt Dydol");
        bankDataManager.addAccount("839393948290202", "Kamil Kuznik");

        System.out.println(bankDataManager.getAccountCount());

        bankDataManager.addTransaction("ATM Withdrawal");
        bankDataManager.addTransaction("ATM Withdrawal");
        bankDataManager.addTransaction("Shopping");
        bankDataManager.addTransaction("Money Transfer");
        bankDataManager.addTransaction("Money Transfer - international");
        System.out.println(bankDataManager.getTransactionHistory());
        System.out.println(bankDataManager.isAccountActive("839393948290202"));
        System.out.println(bankDataManager.isAccountActive("8e7456384794734"));

        System.out.println(bankDataManager.getAccountHolder("383746292902734"));
    }
}
