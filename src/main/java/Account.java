public class Account {
  private final String accountId;
  private int balance;

  public Account(String accountId, int balance) {
    this.accountId = accountId;
    this.balance = balance;
  }

  public String getAccountId() {
    return accountId;
  }

  public int getBalance() {
    return balance;
  }

  public void deposit(int amount) {
    balance += amount;
  }

  public boolean withdraw(int amount) {
    if (amount <= balance) {
      balance -= amount;
      return true;
    }
    return false;
  }
}
