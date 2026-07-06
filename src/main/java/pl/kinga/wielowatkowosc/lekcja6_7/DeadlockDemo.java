package pl.kinga.wielowatkowosc.lekcja6_7;

import java.math.BigDecimal;

public class DeadlockDemo {

    static class BankAccount {
        private final String id;
        private BigDecimal balance;

        BankAccount(String id, BigDecimal balance) {
            this.id = id;
            this.balance = balance;

        }

        String getId() {
            return id;
        }

        BigDecimal getBalance() {
            return balance;
        }
    }

    public static void transferUnsafe(BankAccount from, BankAccount to, BigDecimal amount) throws InterruptedException {
        synchronized (from) {
            Thread.sleep(100);
            synchronized (to) {
                from.balance = from.balance.subtract(amount);
                to.balance = to.balance.add(amount);
                System.out.println("Transfer [" + from.id + "] -> [" + to.id + "]: [" + amount + "] PLN");
            }
        }
    }

    public static void transferSafe(BankAccount from, BankAccount to, BigDecimal amount) throws InterruptedException {
        BankAccount first = from.getId().compareTo(to.getId()) < 0 ? from : to;
        BankAccount second = from.getId().compareTo(to.getId()) < 0 ? to : from;
        synchronized (first) {
            Thread.sleep(100);
            synchronized (second) {
                from.balance = from.balance.subtract(amount);
                to.balance = to.balance.add(amount);
                System.out.println("Transfer [" + from.id + "] -> [" + to.id + "]: [" + amount + "] PLN");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount1 = new BankAccount("ACC-1", new BigDecimal("5000"));
        BankAccount bankAccount2 = new BankAccount("ACC-2", new BigDecimal("3000"));

        Thread thread1 = new Thread(() -> {
            try {
                transferSafe(bankAccount1, bankAccount2, new BigDecimal("200"));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                transferSafe(bankAccount2, bankAccount1, new BigDecimal("100"));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join(5000);
        thread2.join(5000);

        System.out.println("Account 1 balance: " + bankAccount1.balance + " PLN");
        System.out.println("Account 2 balance: " + bankAccount2.balance + " PLN");

        // ** Part I **
        // Account 1 balance: 5000 PLN
        //Account 2 balance: 3000 PLN
        // i nie ma zmiany na nowe wartosci progrma dalej nie dziala zawiesil sie -> deadlock

        // ** Part II **
        //Transfer [ACC-1] -> [ACC-2]: [200] PLN
        //Transfer [ACC-2] -> [ACC-1]: [100] PLN
        //Account 1 balance: 4900 PLN
        //Account 2 balance: 3100 PLN
        //Program konczy sie wyswietla sie informacja o transwerze
        //Balance kazdego konta sie zmienia
        // stala kolejnosc blokowania lamie circular wait


    }
}
