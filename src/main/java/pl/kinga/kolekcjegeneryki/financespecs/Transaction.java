package pl.kinga.kolekcjegeneryki.financespecs;

public class Transaction {
    private String id;
    private double amount;

    public Transaction(String id, double amount){
        if (id == null || id.isEmpty() || amount <0 ){
            throw new IllegalArgumentException("Input value is incorrect!");
        }
        this.id = id;
        this.amount = amount;
    }

    public String getId(){
        return id;
    }

    public double getAmount(){
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                '}';
    }
}
