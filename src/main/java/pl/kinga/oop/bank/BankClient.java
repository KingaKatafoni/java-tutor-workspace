package pl.kinga.oop.bank;

import java.util.Objects;

public class BankClient {
    private String personalId;
    private String name;
    private String email;

    public BankClient(String personalId, String name, String email) {
        this.personalId = personalId;
        this.name = name;
        this.email = email;
    }

    public String getPersonalId() {
        return personalId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "BankClient{" +
                "personalId='" + personalId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BankClient other = (BankClient) obj;
        return personalId.equals(other.personalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalId);
    }
}
