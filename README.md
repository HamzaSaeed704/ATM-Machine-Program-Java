# ATM-Machine-Program-Java
Here is The Project which includes appealing GUI for the ATM program using Java Swing

Below is a detailed explanation of the ATM program with a polished GUI using Java Swing.

Overall Structure
The program is split into three main parts:

1 . Account.java: Handles the account details and operations.
2 . Transaction.java: Represents a transaction with type and amount.
3 . ATM.java: The main class that sets up the GUI and handles user interactions.

Account.java
This class encapsulates the account details and provides methods to interact with the account.

Fields:

1. accountNumber: Stores the account number.
2. pin: Stores the PIN for account verification.
3. balance: Stores the account balance.
4. transactionHistory: A list to keep track of transactions.

Methods:

1. getBalance(): Returns the current balance.
2. withdraw(double amount): Withdraws the specified amount if sufficient balance is available.
3. deposit(double amount): Deposits the specified amount into the account.
4. verifyPin(int pin): Verifies the entered PIN.
5. setPin(int newPin): Sets a new PIN.
6. getTransactionHistory(): Returns the transaction history.


Transaction.java
This class represents a single transaction.

Fields:

1. type: Type of transaction (e.g., "Withdrawal" or "Deposit").
2. amount: Amount involved in the transaction.

Methods:

1. toString(): Returns a string representation of the transaction.

ATM.java
This is the main class that creates the GUI and handles user interactions.

Fields:

1. account: An instance of the Account class.
2. cardLayout: A CardLayout to switch between different panels (screens).
3. mainPanel: The main panel that holds all other panels.

Constructor:

1. Initializes the account and calls createGUI() to set up the GUI.

JFrame:

1. Creates the main application window.
2. Sets the title, default close operation, size, and visibility.

Panels:

1. mainPanel: The main panel with a CardLayout to switch between different screens.
2. menuPanel, balancePanel, withdrawPanel, depositPanel, pinChangePanel, historyPanel: Different panels for each ATM functionality.


Adding Panels:

1. Adds each panel to the mainPanel with a specific name for switching.
