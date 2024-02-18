# Simple-ATM

### **Building the Project**

To build the Project, use the following command:

```bash
./gradlew build
```

Or, on Windows:

```bash
gradlew.bat build
```


### **Running the Program**

To run the program, use the following command after building:

```bash
java -cp build/classes/java/main ATM
```

### **Running the Tests**

To run the tests, use the following command:

```bash
./gradlew test
```

Or, on Windows:

```bash
gradlew.bat test
```

### **Project Structure**

- **`src/main/java/`**
    - **`ATMController.java`**: Core logic for ATM operations.
    - **`Account.java`**: Represents a bank account.
    - **`BankSystemInterface.java`**: Interface for the banking system integration.
    - **`BankSystemMock.java`**: Mock implementation of the banking system.
- **`src/test/java/`**
    - **`ATMControllerTest.java`**: Tests for the ATMController.

## **Process Flow**

The application simulates an ATM machine process flow as follows:

### **1. Setting Up Accounts**
Before running the main ATM process, the application allows the setup of mock accounts associated with a card. This setup process involves entering a card number, PIN, and details of one or more accounts (account ID and initial balance).
### **2. Insert Card**
### **3. Enter PIN**
### **4. Select Account**
### **5. Choose Operation**
- Once an account is selected, the user can choose to:
  - Check Balance
  - Deposit
  - Withdraw
  - Exit