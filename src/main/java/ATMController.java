import java.util.List;

public class ATMController {
  private final BankSystemInterface bankSystem;
  private Account currentAccount;

  public ATMController(BankSystemInterface bankSystem) {
    this.bankSystem = bankSystem;
  }

  public boolean insertCardAndPin(String cardNumber, int pin) {
    return bankSystem.verifyPIN(cardNumber, pin);
  }

  public List<Account> getAccountsForCard(String cardNumber) {
    return bankSystem.getAccountsForCard(cardNumber);
  }

  public void selectAccount(String cardNumber, int accountIndex) {
    List<Account> accounts = getAccountsForCard(cardNumber);
    if(accountIndex >= 0 && accountIndex < accounts.size()) {
      this.currentAccount = accounts.get(accountIndex);
    } else {
      throw new IllegalArgumentException("Invalid account selection.");
    }
  }

  public int checkBalance() {
    return currentAccount.getBalance();
  }

  public boolean deposit(int amount) {
    currentAccount.deposit(amount);
    return true;
  }

  public boolean withdraw(int amount) {
    return currentAccount.withdraw(amount);
  }
}
