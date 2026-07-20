package pl.kinga.testowanie.mini_project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    PaymentGateway paymentGateway;

    @Mock
    ReceiptService receiptService;

    @InjectMocks
    PaymentService paymentService;

    //processPayment
    @Test
    void shouldReturnPaymentResultWhenPaymentApproved() {
        Customer customer = new Customer("C-001", "Anna Nowak", "anna.nowak@gmail.com", true);
        when(customerRepository.findById("C-001")).thenReturn(customer);
        when(paymentGateway.charge("C-001", 200.0)).thenReturn(new PaymentResult("TRX-001", "C-001", 200.0, PaymentStatus.APPROVED, "Approved"));

        PaymentResult result = paymentService.processPayment("C-001", 200.0);

        assertEquals(PaymentStatus.APPROVED, result.status());
        verify(receiptService).sendReceipt("anna.nowak@gmail.com", "TRX-001", 200.0);
    }

    @Test
    void shouldReturnPaymentWithDeclineWhenPaymentDeclined() {
        Customer customer = new Customer("C-001", "Anna Nowak", "anna.nowak@gmail.com", true);
        when(customerRepository.findById("C-001")).thenReturn(customer);
        when(paymentGateway.charge("C-001", 200.0)).thenReturn(new PaymentResult("TRX-001", "C-001", 200.0, PaymentStatus.DECLINED, "Declined"));

        PaymentResult result = paymentService.processPayment("C-001", 200.0);

        assertEquals(PaymentStatus.DECLINED, result.status());
        verify(receiptService, never()).sendReceipt(anyString(), anyString(), anyDouble());
    }

    @Test
    void shouldReturnPaymentResultFieldsWhenPaymentApproved() {
        Customer customer = new Customer("C-001", "Anna Nowak", "anna.nowak@gmail.com", true);
        when(customerRepository.findById("C-001")).thenReturn(customer);
        when(paymentGateway.charge("C-001", 200.0)).thenReturn(new PaymentResult("TRX-001", "C-001", 200.0, PaymentStatus.APPROVED, "Approved"));

        PaymentResult result = paymentService.processPayment("C-001", 200.0);
        assertAll(
                () -> assertEquals("TRX-001", result.transactionId()),
                () -> assertEquals("C-001", result.customerId()),
                () -> assertEquals(200.0, result.amount()),
                () -> assertEquals(PaymentStatus.APPROVED, result.status())
        );
    }

    //validation

    @Test
    void shouldThrowIllegalArgumentWhenCustomerIdInProcessIsNull() {

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> paymentService.processPayment(null, 900.0));

        assertEquals("Customer ID is required", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenCustomerIdInProcessIsEmpty() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> paymentService.processPayment("", 900.0));

        assertEquals("Customer ID is required", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenAmountIsLessThanMin() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> paymentService.processPayment("C-001", 0.5));

        assertEquals("Amount must be at least 1.0", ex.getMessage());
        verify(customerRepository, never()).findById(anyString());
        verify(paymentGateway, never()).charge(anyString(), anyDouble());
        verify(receiptService, never()).sendReceipt(anyString(), anyString(), anyDouble());
    }

    @Test
    void shouldThrowIllegalArgumentWhenAmountIsHigherThanMax() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> paymentService.processPayment("C-001", 60000.0));

        assertEquals("Amount cannot exceed 50000.0", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenAmountIsEqualZero() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> paymentService.processPayment("C-001", 0.0));

        assertEquals("Amount must be at least 1.0", ex.getMessage());
    }

    //client
    @Test
    void shouldThrowCustomerNotFoundWhenCustomerDoesNotExist() {
        CustomerNotFoundException ex = assertThrows(CustomerNotFoundException.class,
                () -> paymentService.processPayment("C-001", 90.0));
        assertEquals("C-001", ex.getCustomerId());
    }

    @Test
    void shouldThrowInactiveCustomerWhenCustomerInactive() {
        Customer customer = new Customer("C-001", "Anna Nowak", "anna.nowak@gmail.com", false);
        when(customerRepository.findById("C-001")).thenReturn(customer);

        InactiveCustomerException ex = assertThrows(InactiveCustomerException.class,
                () -> paymentService.processPayment("C-001", 90.0));

        assertEquals("C-001", ex.getCustomerId());
        verify(paymentGateway, never()).charge(anyString(), anyDouble());
        verify(receiptService, never()).sendReceipt(anyString(), anyString(), anyDouble());
    }

    @Test
    void shouldThrowInstanceInactiveWhenCustomerInactive() {
        Customer customer = new Customer("C-001", "Anna Nowak", "anna.nowak@gmail.com", false);
        when(customerRepository.findById("C-001")).thenReturn(customer);

        InactiveCustomerException ex = assertThrows(InactiveCustomerException.class,
                () -> paymentService.processPayment("C-001", 90.0));
        assertInstanceOf(InactiveCustomerException.class, ex);
    }

    //gateway problems
    @Test
    void shouldThrowPaymentProcessingExceptionWhenGatewayThrowsRuntime() {
        Customer customer = new Customer("C-001", "Anna Nowak", "anna.nowak@gmail.com", true);
        when(customerRepository.findById("C-001")).thenReturn(customer);
        doThrow(new RuntimeException("Database connection lost")).when(paymentGateway).charge("C-001", 100.0);

        PaymentProcessingException ex = assertThrows(PaymentProcessingException.class,
                () -> paymentService.processPayment("C-001", 100.0));

        assertInstanceOf(RuntimeException.class, ex.getCause());
        assertEquals("Payment gateway error", ex.getMessage());
        assertEquals("TXN-FAILED", ex.getTransactionId());
    }

    @Test
    void shouldThrowAndVerifyWhenGatewayThrowsRuntime() {
        Customer customer = new Customer("C-001", "Anna Nowak", "anna.nowak@gmail.com", true);
        when(customerRepository.findById("C-001")).thenReturn(customer);
        doThrow(new RuntimeException("Database connection lost")).when(paymentGateway).charge("C-001", 100.0);

        PaymentProcessingException ex = assertThrows(PaymentProcessingException.class,
                () -> paymentService.processPayment("C-001", 100.0));

        verify(receiptService, never()).sendReceipt(anyString(), anyString(), anyDouble());
    }

    @ParameterizedTest
    @CsvSource({
            "1.0",
            "100.0",
            "25000.0",
            "50000.0"
    })
    void shouldReturnTrueWhenAmountCorrect(double correctAmount) {
        assertTrue(paymentService.validatePaymentAmount(correctAmount));
    }

    @ParameterizedTest
    @CsvSource({
            "0.0",
            "0.5",
            "0.99",
            "-100.0"
    })
    void shouldReturnFalseWhenAmountIncorrect(double incorrectValue) {
        assertFalse(paymentService.validatePaymentAmount(incorrectValue));
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            1.0,
            50000.0
    })
    void shouldReturnTrueWhenLimitValue(double amount) {
        assertTrue(paymentService.validatePaymentAmount(amount));
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            0.99,
            50000.01
    })
    void shouldReturnFalseWhenAmountOutOfRange(double amount) {
        assertFalse(paymentService.validatePaymentAmount(amount));
    }

    @Test
    void shouldNotThrowWhenAmountExactlyMin() {
        Customer customer = new Customer("C-001", "Anna Nowak", "anna.nowak@gmail.com", true);
        when(customerRepository.findById("C-001")).thenReturn(customer);
        when(paymentGateway.charge("C-001", 1.0)).thenReturn(new PaymentResult("TRX-001", "C-001", 1.0, PaymentStatus.APPROVED, "Approved"));

        assertDoesNotThrow(() -> paymentService.processPayment("C-001", 1.0));
    }

    @Test
    void shouldNotThrowWhenAmountExactlyMax() {
        Customer customer = new Customer("C-001", "Anna Nowak", "anna.nowak@gmail.com", true);
        ;
        when(customerRepository.findById("C-001")).thenReturn(customer);
        when(paymentGateway.charge("C-001", 50000.0)).thenReturn(new PaymentResult("TRX-001", "C-001", 50000.0, PaymentStatus.APPROVED, "Approved"));

        assertDoesNotThrow(() -> paymentService.processPayment("C-001", 50000.0));
    }

    @Test
    void shouldVerifyFindByIdWhenExecutedOnce() {
        Customer customer = new Customer("C-001", "Anna Nowak", "anna.nowak@gmail.com", true);

        when(customerRepository.findById("C-001")).thenReturn(customer);
        when(paymentGateway.charge("C-001", 5000.0)).thenReturn(new PaymentResult("TRX-001", "C-001", 5000.0, PaymentStatus.APPROVED, "Approved"));

        PaymentResult paymentResult = paymentService.processPayment("C-001", 5000.0);

        verify(customerRepository, times(1)).findById(anyString());
    }

    @Test
    void shouldVerifyFindByIdWhenExecutedOnceWithCorrectArgs() {
        Customer customer = new Customer("C-001", "Anna Nowak", "anna.nowak@gmail.com", true);

        when(customerRepository.findById("C-001")).thenReturn(customer);
        when(paymentGateway.charge("C-001", 5000.0)).thenReturn(new PaymentResult("TRX-001", "C-001", 5000.0, PaymentStatus.APPROVED, "Approved"));

        PaymentResult paymentResult = paymentService.processPayment("C-001", 5000.0);

        verify(paymentGateway, times(1)).charge("C-001", 5000.0);
    }

    @Test
    void shouldVerifyAllMocksIdWhenValidationError() {

        assertThrows(IllegalArgumentException.class,
                () -> paymentService.processPayment("", 5000.0));

        verify(paymentGateway, never()).charge(anyString(), anyDouble());
        verify(customerRepository, never()).findById(anyString());
        verify(receiptService, never()).sendReceipt(anyString(), anyString(), anyDouble());
    }


}