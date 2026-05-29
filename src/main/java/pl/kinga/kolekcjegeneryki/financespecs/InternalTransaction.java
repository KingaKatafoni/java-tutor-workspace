package pl.kinga.kolekcjegeneryki.financespecs;

public class InternalTransaction extends Transaction{
    private String department;

    public InternalTransaction(String id, double amount, String department) {
        super(id, amount);
        if (department == null || department.isEmpty()){
            throw new IllegalArgumentException("Input value is incorrect!");
        }
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "InternalTransaction{" + super.toString() + " " +
                "department='" + department + '\'' +
                '}';
    }
}
