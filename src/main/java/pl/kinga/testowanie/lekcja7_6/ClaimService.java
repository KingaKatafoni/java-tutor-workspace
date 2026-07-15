package pl.kinga.testowanie.lekcja7_6;

public class ClaimService {
    private final PolicyRepository policyRepository;
    private final NotificationService notificationService;

    public ClaimService(PolicyRepository policyRepository, NotificationService notificationService){
        this.policyRepository = policyRepository;
        this.notificationService = notificationService;
    }

    public String submitClaim(String policyId, double amount){

        if(policyId == null || policyId.isEmpty()){
            throw new IllegalArgumentException("Policy ID cannot be null or empty");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Claim amount must be positive");
        }

        Policy policyRepositoryById = policyRepository.findById(policyId);
        if (policyRepositoryById == null){
            throw new IllegalArgumentException("Policy not found: " + policyId);
        }

        if (!policyRepositoryById.active()){
            notificationService.sendRejectionNotification(policyRepositoryById.holderName(), policyId,"Policy is inactive");
            return "Claim rejected: policy inactive";
        } else {
            notificationService.sendApprovalNotification(policyRepositoryById.holderName(), policyId);
            return "Claim approved for policy: " + policyId;
        }
    }

    public double getClaimLimit(String policyId){
        Policy policyRepositoryById = policyRepository.findById(policyId);

        if (policyRepositoryById == null){
            throw new IllegalArgumentException("Policy not found: " + policyId);
        }

        //types: "AUTO"`, `"HOME"`, `"HEALTH"`, `"TRAVEL"`
        return switch (policyRepositoryById.type()){
           case "AUTO" -> 50000.0;
           case "HOME" -> 200000.0;
           case "HEALTH" -> 100000.0;
           case "TRAVEL" -> 20000.0;
           default -> throw new IllegalArgumentException("Unknown policy type: " + policyRepositoryById.type());
        };
    }
}
