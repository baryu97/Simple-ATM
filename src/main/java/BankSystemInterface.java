import java.util.List;

public interface BankSystemInterface {
  boolean verifyPIN(String cardNumber, int pin);
  List<Account> getAccountsForCard(String accountId);
}
