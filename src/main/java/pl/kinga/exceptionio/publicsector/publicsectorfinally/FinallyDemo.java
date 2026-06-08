package pl.kinga.exceptionio.publicsector.publicsectorfinally;

public class FinallyDemo {
    public static String noException() {
        try {
            System.out.println("try");
            return "no exception";
        } finally {
            System.out.println("finally");
        }
    }

    public static String withException() {
        try {
            System.out.println("try");
            throw new RuntimeException("boom");
        } catch (RuntimeException e) {
            System.out.println("catch: " + e.getMessage());
            return "caught";
        } finally {
            System.out.println("finally");
        }
    }

    public static int returnInFinally() {
        try {
            return 1;
        } finally {
            return 2;
        }
    }

    public static String exceptionNotCaught() {
        try {
            System.out.println("try");
            throw new IllegalStateException("error");
        } catch (NullPointerException e) {
            System.out.println("catch");
            return "catch";
        } finally {
            System.out.println("finally");

        }
    }


    public static void main(String[] args) {
        noException();  //print: try then finally
        //return: no exception
        System.out.println("Return: "  + noException());
        withException(); //print: try -> catch: boom -> finally
        //return: caught
        System.out.println("Return: " + withException());
        System.out.println(returnInFinally()); // return 2

        try {
            exceptionNotCaught();
        } catch (Exception e) {
            System.out.println("Error");
        }

    }
}
