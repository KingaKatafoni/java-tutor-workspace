package pl.kinga.testowanie.lekcja7_6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClaimServiceTest {

    @Mock
    PolicyRepository policyRepository;

    @Mock
    NotificationService notificationService;

    @InjectMocks
    ClaimService claimService;

    //submitClaim

    @Test
    void shouldReturnClaimApprovedWhenPolicyActive() {
        // arrange
        Policy policy = new Policy("P-001", "Anna Kalka", "HOME", true);
        when(policyRepository.findById("P-001")).thenReturn(policy);

        //act
        String result = claimService.submitClaim("P-001", 5000.0);

        //assert
        assertEquals("Claim approved for policy: P-001", result);
    }

    @Test
    void shouldReturnClaimRejectedWhenPolicyInactive() {
        Policy policy = new Policy("P-001", "Anna Kalka", "HOME", false);
        when(policyRepository.findById("P-001")).thenReturn(policy);

        String result = claimService.submitClaim("P-001", 100000.0);

        assertEquals("Claim rejected: policy inactive", result);
    }

    @Test
    void shouldThrowIllegalArgumentNotFoundWhenPolicyNotFound() {

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> claimService.submitClaim("P-002", 40000.0));

        assertEquals("Policy not found: P-002", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenPolicyIdNull(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> claimService.submitClaim(null, 90000.00)
        );

        assertEquals("Policy ID cannot be null or empty", ex.getMessage());
    }
    @Test
    void shouldThrowIllegalArgumentWhenPolicyIdEmpty(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> claimService.submitClaim("", 90000.00)
        );

        assertEquals("Policy ID cannot be null or empty", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenAmountZero(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> claimService.submitClaim("P-001", 0.0));
        assertEquals("Claim amount must be positive", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenAmountNegative(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> claimService.submitClaim("P-001", -10000.0));
        assertEquals("Claim amount must be positive", ex.getMessage());
    }

    //getClaimLimit
    @ParameterizedTest
    @CsvSource({
            "AUTO, 50000.0",
            "HOME, 200000.0",
            "HEALTH, 100000.0",
            "TRAVEL, 20000.0"
    })
    void shouldReturnLimitWhenTypeCorrect(String type, double amount){
        Policy policy = new Policy("P-009", "PO Lo", type, true);
        when(policyRepository.findById(Mockito.anyString())).thenReturn(policy);

        double limit = claimService.getClaimLimit(policy.policyId());
        assertEquals(amount, limit);
    }

    @Test
    void shouldThrowIllegalArgumentWhenPolicyNotFound(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> claimService.getClaimLimit("P-002")
        );
        assertEquals("Policy not found: P-002", ex.getMessage());
    }




}