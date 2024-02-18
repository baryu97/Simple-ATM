import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BankSystemMock implements BankSystemInterface {
  private Map<String, String> cardPinMap = new HashMap<>();
  private Map<String, List<Account>> cardAccountsMap = new HashMap<>();

  public void addAccountToCard(String cardNumber, String pin, Account account) {
    cardPinMap.putIfAbsent(cardNumber, pin);
    cardAccountsMap.computeIfAbsent(cardNumber, k -> new ArrayList<>()).add(account);
  }

  @Override
  public boolean verifyPIN(String cardNumber, int pin) {
    String storedPin = cardPinMap.get(cardNumber);
    return storedPin != null && storedPin.equals(String.valueOf(pin));
  }

  @Override
  public List<Account> getAccountsForCard(String cardNumber) {
    return cardAccountsMap.getOrDefault(cardNumber, new ArrayList<>());
  }
}