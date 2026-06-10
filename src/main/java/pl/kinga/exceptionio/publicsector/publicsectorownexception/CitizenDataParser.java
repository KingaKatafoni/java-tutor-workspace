package pl.kinga.exceptionio.publicsector.publicsectorownexception;

public class CitizenDataParser {
    public int parseLine(String line, int lineNumber) throws CitizenImportException {
       try {
           return Integer.parseInt(line);
       } catch (NumberFormatException e){
           throw new CitizenImportException("Invalid age data", lineNumber, e);
       }
    }
}
