package pl.kinga.exceptionio.insurance;

public class Main {
    public static void main(String[] args) {
        InsuranceService insuranceService = new InsuranceService();
        System.out.println("-----------1-------------");
        try {
            insuranceService.claimInsurance("POL-001");
        } catch (RuntimeException e) {
            System.out.println("Correct policy number!");
        }

        System.out.println("-----------2-------------");
        try {
            insuranceService.claimInsurance("UNKNOWN");
        } catch (PolicyNotFoundException e){
            System.out.println(e.getMessage());
        }
        System.out.println("-----------3-------------");
        try {
          insuranceService.claimInsurance("EXP-001");
        } catch (PolicyExpiredException e) {
            System.out.println(e.getMessage());
        }

    }
}
