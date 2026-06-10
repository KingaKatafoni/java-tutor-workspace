package pl.kinga.exceptionio.publicsector.publicsectorownexception;

public class CitizenImportException extends Exception {
    private final int lineNumber;

    public CitizenImportException(String message, int lineNumber, Throwable cause){
        super(message, cause);
        this.lineNumber = lineNumber;
    }

    public int getLineNumber(){
        return lineNumber;
    }
}
