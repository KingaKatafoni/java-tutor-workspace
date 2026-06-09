package pl.kinga.exceptionio.publicsector.publicsectortryresources;

public class Main {
    public static void main(String[] args){
        try(AuditSession auditSession = new AuditSession("AUD-001")){
            auditSession.performAudit("Finance");
        } catch (Exception e){
            System.out.println("Main exception: " + e.getMessage());
            for (Throwable s : e.getSuppressed()){
                System.out.println("Suppressed: " + s.getMessage());
            }
        }
    }
}
