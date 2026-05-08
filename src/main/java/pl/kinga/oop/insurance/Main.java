package pl.kinga.oop.insurance;

import java.util.HashSet;

public class Main {
    public static void main(String[] args){
        InsurancePolicy insurancePolicyOne = new InsurancePolicy("IP/123/001", "Krystian Popek");
        InsurancePolicy insurancePolicyTwo = new InsurancePolicy("IP/123/001", "Alicja Drabina");

        System.out.print("insurancePolicyOne == insurancePolicyTwo: ");
        System.out.println(insurancePolicyOne == insurancePolicyTwo);
        System.out.print("insurancePolicyOne.equals(insurancePolicyTwo): ");
        System.out.println(insurancePolicyOne .equals(insurancePolicyTwo));
        System.out.print("insurancePolicyOne.hashCode() == insurancePolicyTwo.hashCode(): ");
        System.out.println(insurancePolicyOne .hashCode() == insurancePolicyTwo.hashCode());

        System.out.println("insurancePolicyOne.hashCode(): " + insurancePolicyOne.hashCode());
        System.out.println("insurancePolicyTwo.hashCode(): " + insurancePolicyTwo.hashCode());

        HashSet<InsurancePolicy> insurancePolicies = new HashSet<>();
        insurancePolicies.add(insurancePolicyOne);
        System.out.println("HashSet contains: " + insurancePolicies.contains(insurancePolicyTwo));
        // contains() zwrocilo false mimo ze equals() mowi true,
        // poniewaz nadpisalismy equals i sprawdzamy po policyNumber
        // a hashCode() pozostał nienadpisany, wiec brana jest pod uwage referencja a nie ustawione przeze mnie pole
        // a referencje dla tych dwoch obiektow sie roznia


    }
}
