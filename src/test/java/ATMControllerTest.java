import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ATMControllerTest {
  private ATMController atmController;
  @Mock
  private BankSystemInterface mockBankSystem;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    when(mockBankSystem.verifyPIN("12345678", 1234)).thenReturn(true);
    when(mockBankSystem.getAccountsForCard("12345678")).thenReturn(Arrays.asList(
        new Account("acc123", 500),
        new Account("acc456", 1000)
    ));

    atmController = new ATMController(mockBankSystem);
  }

  @Test
  void testInsertCardAndPin() {
    assertTrue(atmController.insertCardAndPin("12345678", 1234), "PIN should be verified successfully.");
  }

  @Test
  void testSelectAccountAndCheckBalance() {
    atmController.selectAccount("12345678", 0);
    assertEquals(500, atmController.checkBalance(), "Balance should match the first account.");
  }

  @Test
  void testDeposit() {
    atmController.selectAccount("12345678", 0);
    assertTrue(atmController.deposit(100), "Deposit should succeed.");
    assertEquals(600, atmController.checkBalance(), "Balance after deposit should be updated.");
  }

  @Test
  void testWithdraw() {
    atmController.selectAccount("12345678", 0);
    assertTrue(atmController.withdraw(200), "Withdraw should succeed.");
    assertEquals(300, atmController.checkBalance(), "Balance after withdrawal should be updated.");
  }

  @Test
  void testInvalidAccountSelection() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      atmController.selectAccount("12345678", 10);
    });

    String expectedMessage = "Invalid account selection.";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage), "Exception message should indicate invalid selection.");
  }
}
