import java.util.List;
import java.util.Scanner;

public class ATM {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    BankSystemMock bankSystem = new BankSystemMock();
    setting(bankSystem);
    ATMController atmController = new ATMController(bankSystem);

    System.out.println("ATM System Setup Complete.");
    System.out.println("Please insert your card (enter card number):");
    String cardNumber = scanner.nextLine();

    System.out.println("Enter PIN:");
    int pin = scanner.nextInt();
    scanner.nextLine();

    if (!atmController.insertCardAndPin(cardNumber, pin)) {
      System.out.println("Incorrect PIN. Exiting...");
      return;
    }

    List<Account> accounts = atmController.getAccountsForCard(cardNumber);
    if (accounts.isEmpty()) {
      System.out.println("No accounts found for this card. Exiting...");
      return;
    }

    System.out.println("Select an account:");
    for (int i = 0; i < accounts.size(); i++) {
      System.out.println(i + 1 + ". Account ID: " + accounts.get(i).getAccountId());
    }
    int accountChoice = scanner.nextInt() - 1;
    scanner.nextLine();

    atmController.selectAccount(cardNumber, accountChoice);

    boolean exit = false;
    while (!exit) {
      System.out.println("Choose operation:");
      System.out.println("1. Check Balance");
      System.out.println("2. Deposit");
      System.out.println("3. Withdraw");
      System.out.println("4. Exit");
      int choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1: // Check Balance
          System.out.println("Your balance is: " + atmController.checkBalance());
          break;
        case 2: // Deposit
          System.out.print("Enter amount to deposit: ");
          int depositAmount = scanner.nextInt();
          scanner.nextLine();
          atmController.deposit(depositAmount);
          System.out.println("Deposited successfully. New balance: " + atmController.checkBalance());
          break;
        case 3: // Withdraw
          System.out.print("Enter amount to withdraw: ");
          int withdrawAmount = scanner.nextInt();
          scanner.nextLine();
          if (atmController.withdraw(withdrawAmount)) {
            System.out.println("Withdrawn successfully. Remaining balance: " + atmController.checkBalance());
          } else {
            System.out.println("Insufficient funds.");
          }
          break;
        case 4:
          exit = true;
          break;
        default:
          System.out.println("Invalid choice. Please try again.");
          break;
      }
    }

    System.out.println("Thank you for using the ATM. Goodbye!");
  }

  static void setting(BankSystemMock bankSystem) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to the ATM System Setup");

    System.out.print("Enter card number: ");
    String cardNumber = scanner.nextLine();

    System.out.print("Enter PIN for this card: ");
    String pin = scanner.nextLine();

    System.out.print("Enter number of accounts for this card: ");
    int numAccounts = scanner.nextInt();
    scanner.nextLine();

    for (int j = 0; j < numAccounts; j++) {
      System.out.println("Setting up account " + (j + 1));

      System.out.print("Enter account ID: ");
      String accountId = scanner.nextLine();

      System.out.print("Enter initial balance: ");
      int balance = scanner.nextInt();
      scanner.nextLine();

      Account account = new Account(accountId, balance);
      bankSystem.addAccountToCard(cardNumber, pin, account);
    }
  }
}
