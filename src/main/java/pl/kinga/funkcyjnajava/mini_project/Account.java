package pl.kinga.funkcyjnajava.mini_project;

import java.time.LocalDate;

public record Account(String accountId, String ownerName, String type, LocalDate openedDate) {
}
