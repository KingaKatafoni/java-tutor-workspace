package pl.kinga.testowanie.lekcja7_9;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BankServiceTest {

    @Mock
    AccountRepository accountRepository;
    @InjectMocks
    BankService bankService;

    @Test
    void shouldReturnBalanceWhenAccountExists() {
        Account account = new Account("ACC-001", "Adam Bialy", 6000.00);
        when(accountRepository.findById("ACC-001")).thenReturn(account);

        double balance = bankService.getBalance(account.accountId());

        assertEquals(6000.0, balance);
    }

    @Test
    void shouldThrowAccountNotFoundWhenAccountDoesNotExist() {

        AccountNotFoundException ex = assertThrows(AccountNotFoundException.class,
                () -> bankService.getBalance("ACC-002"));
        assertEquals("Account not found: ACC-002", ex.getMessage());
        assertEquals("ACC-002", ex.getAccountId());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionInGetBalanceWhenAccountIdIsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> bankService.getBalance(null));
        assertEquals("Account ID is required", ex.getMessage());
    }

    //withdraw
    @Test
    void shouldReturnWithdrawInfoWhenCorrect() {
        Account account = new Account("ACC-001", "Adam Bialy", 6000.00);
        when(accountRepository.findById("ACC-001")).thenReturn(account);

        String withdraw = bankService.withdraw(account.accountId(), 300);

        assertEquals("Withdrawn 300.0 from ACC-001", withdraw);
        verify(accountRepository).updateBalance("ACC-001", 5700.00);
    }

    @Test
    void shouldThrowInsufficientFundsExceptionWhenLackOfFunds() {
        Account account = new Account("ACC-001", "Adam Bialy", 600.00);
        when(accountRepository.findById("ACC-001")).thenReturn(account);

        InsufficientFundsException ex = assertThrows(InsufficientFundsException.class,
                () -> bankService.withdraw("ACC-001", 700.0));

        assertAll(
                () -> assertEquals("Insufficient funds on account ACC-001: requested 700.0, available 600.0", ex.getMessage()),
                () -> assertEquals("ACC-001", ex.getAccountId()),
                () -> assertEquals(700.0, ex.getRequested()),
                () -> assertEquals(600.0, ex.getAvailable())
        );
    }

    @Test
    void shouldThrowAccountNotFoundWhenWithdraw() {
        when(accountRepository.findById("ACC-001")).thenReturn(null);

        AccountNotFoundException ex = assertThrows(AccountNotFoundException.class,
                () -> bankService.withdraw("ACC-001", 400.0));
        assertEquals("Account not found: ACC-001", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenWithdrawAmountEqualsZero() {

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> bankService.withdraw("ACC-001", 0.0));

        assertEquals("Amount must be positive", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenWithdrawAmountBelowZero() {

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> bankService.withdraw("ACC-001", -23.0));

        assertEquals("Amount must be positive", ex.getMessage());
    }

    @Test
    void shouldThrowTransactionExceptionWhenUpdateBalance() {
        Account account = new Account("ACC-001", "Adam Bialy", 600.00);
        when(accountRepository.findById("ACC-001")).thenReturn(account);
        doThrow(new RuntimeException("Database connection lost")).when(accountRepository).updateBalance("ACC-001", 100.0);

        TransactionException ex = assertThrows(TransactionException.class, () -> bankService.withdraw("ACC-001", 500.0));
        assertInstanceOf(RuntimeException.class, ex.getCause());
    }

    @Test
    void shouldNotThrowInsufficientFundsWhenLimit() {
        Account account = new Account("ACC-001", "Adam Bialy", 600.00);
        when(accountRepository.findById("ACC-001")).thenReturn(account);

        assertDoesNotThrow(() -> bankService.withdraw("ACC-001", 600.00));
    }

    //transfer
    @Test
    void shouldReturnInfoWhenCorrectTransfer() {
        Account account1 = new Account("ACC-001", "Adam Bialy", 600.00);
        Account account2 = new Account("ACC-002", "Anna Bialy", 1200.00);
        when(accountRepository.findById("ACC-001")).thenReturn(account1);
        when(accountRepository.findById("ACC-002")).thenReturn(account2);

        String transfer = bankService.transfer(account2.accountId(), account1.accountId(), 300.0);
        assertEquals("Transferred 300.0 from ACC-002 to ACC-001", transfer);
        verify(accountRepository).updateBalance("ACC-001", 900.00);
        verify(accountRepository).updateBalance("ACC-002", 900.00);

    }

    @Test
    void shouldThrowInsufficientFundsExceptionWhenNotEnoughOnFrom() {
        Account account1 = new Account("ACC-001", "Adam Bialy", 600.00);
        Account account2 = new Account("ACC-002", "Anna Bialy", 200.00);
        when(accountRepository.findById("ACC-001")).thenReturn(account1);
        when(accountRepository.findById("ACC-002")).thenReturn(account2);

        InsufficientFundsException ex = assertThrows(InsufficientFundsException.class,
                () -> bankService.transfer(account2.accountId(), account1.accountId(), 300.00));
        assertEquals("Insufficient funds on account ACC-002: requested 300.0, available 200.0", ex.getMessage());
    }

    @Test
    void shouldThrowAccountNotFoundWhenAccountFromDoesNotExist() {
        Account accountTo = new Account("ACC-001", "Adam Bialy", 600.00);
        when(accountRepository.findById("ACC-001")).thenReturn(null);
        when(accountRepository.findById("ACC-002")).thenReturn(accountTo);
        AccountNotFoundException ex = assertThrows(AccountNotFoundException.class,
                () -> bankService.transfer("ACC-001", "ACC-002", 100.0));
        assertEquals("Account not found: ACC-001", ex.getMessage());

    }

    @Test
    void shouldThrowAccountNotFoundWhenAccountToDoesNotExist() {
        Account accountFrom = new Account("ACC-001", "Adam Bialy", 600.00);
        when(accountRepository.findById("ACC-001")).thenReturn(accountFrom);
        when(accountRepository.findById("ACC-002")).thenReturn(null);

        AccountNotFoundException ex = assertThrows(AccountNotFoundException.class,
                () -> bankService.transfer("ACC-001", "ACC-002", 100.0));
        assertEquals("Account not found: ACC-002", ex.getMessage());


    }

    @Test
    void shouldThrowIllegalArgumentWhenAccountIdsAreTheSame() {

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> bankService.transfer("ACC-001", "ACC-001", 100.0));
        assertEquals("Cannot transfer to the same account", ex.getMessage());

    }

    @Test
    void shouldThrowIllegalArgumentWhenAmountBelowZero() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> bankService.transfer("ACC-001", "ACC-002", -300.0));
        assertEquals("Amount must be positive", ex.getMessage());
        verify(accountRepository, never()).updateBalance(anyString(), anyDouble());
    }

    @Test
    void shouldThrowIllegalArgumentWhenAmountEqualsZero() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> bankService.transfer("ACC-001", "ACC-002", 0.0));
        assertEquals("Amount must be positive", ex.getMessage());
        verify(accountRepository, never()).updateBalance(anyString(), anyDouble());
    }

    //additionally
    @Test
    void shouldContainAccountIdInMessage() {
        AccountNotFoundException ex = new AccountNotFoundException("ACC-001");
        assertTrue(ex.getMessage().contains("ACC-001"));
    }

    @Test
    void shouldContainsValueInMessage() {
        InsufficientFundsException ex = new InsufficientFundsException("ACC-001", 400.0, 1200.0);
        assertAll(
                () -> assertTrue(ex.getMessage().contains("ACC-001")),
                () -> assertEquals(400.0, ex.getRequested()),
                () -> assertEquals(1200.0, ex.getAvailable())
        );


    }


}