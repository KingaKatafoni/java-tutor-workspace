package pl.kinga.oop.healthcare;

public record Medicine(String name, String dosage, int quantity) {
    public Medicine{
        if (name == null || name.isEmpty() || dosage == null || dosage.isEmpty() || quantity <= 0 ){
            throw new IllegalArgumentException("Input value is incorrect");
        }
    }

}
