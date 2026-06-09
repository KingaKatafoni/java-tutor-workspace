package pl.kinga.exceptionio.ecommerce;

public class TransactionLog implements AutoCloseable{
    private String logName;

    public TransactionLog(String logName){
        this.logName = logName;
        System.out.println("Transaction log opened: " + logName);
    }

    public void log(String message){
        System.out.println("[LOG] " + message);
    }

    @Override
    public void close(){
        System.out.println("Transaction log closed: " + logName);
    }
}
