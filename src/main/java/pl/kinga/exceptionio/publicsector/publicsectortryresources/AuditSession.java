package pl.kinga.exceptionio.publicsector.publicsectortryresources;

public class AuditSession implements AutoCloseable {
    private String sessionId;

    public AuditSession(String sessionId){
        this.sessionId = sessionId;
        System.out.println("Audit session started: " + sessionId);
    }

    public void performAudit(String department){
        System.out.println("Auditing department: " + department);
        throw new RuntimeException("Audit failed for " + department);
    }

    @Override
    public void close(){
        System.out.println("Closing audit session: " + sessionId);
        throw new RuntimeException("Failed to close session: " + sessionId);
    }
}
