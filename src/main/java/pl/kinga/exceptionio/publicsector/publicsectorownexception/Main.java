package pl.kinga.exceptionio.publicsector.publicsectorownexception;

public class Main {
    public static void main(String[] args){
        CitizenDataParser dataParser = new CitizenDataParser();
        System.out.println("---------25-----------");
        try {
            dataParser.parseLine("25", 2);
        } catch (CitizenImportException e){
            System.out.println("Error at line " + e.getLineNumber() + ": " + e.getMessage());
            System.out.println("Caused by: " + e.getCause());
        }
        System.out.println("---------abc-----------");
        try {
            dataParser.parseLine("abc", 3);
        } catch (CitizenImportException e){
            System.out.println("Error at line " + e.getLineNumber() + ": " + e.getMessage());
            System.out.println("Caused by: " + e.getCause());
        }
        System.out.println("---------40-----------");
        try {
            dataParser.parseLine("40", 2);
        } catch (CitizenImportException e){
            System.out.println("Error at line " + e.getLineNumber() + ": " + e.getMessage());
            System.out.println("Caused by: " + e.getCause());
        }

    }
}
