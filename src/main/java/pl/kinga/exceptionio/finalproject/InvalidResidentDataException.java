package pl.kinga.exceptionio.finalproject;

public class InvalidResidentDataException extends Exception{
    private final int lineNumber;
    private final String reason;

    InvalidResidentDataException(int lineNumber, String reason){
        super(lineNumber + " " + reason);
        this.lineNumber = lineNumber;
        this.reason = reason;
    }

    @Override
    public String getMessage(){
        return "Line " + lineNumber + " : " + reason;
    }

    public int getLineNumber(){
        return lineNumber;
    }
}
