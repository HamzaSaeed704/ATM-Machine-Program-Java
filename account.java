import java.util.ArrayList;

public class account {
    private int accountNumber;
    private int pin;
    private double balance;
    private ArrayList<transaction> transactionHistory;

    public account(int accountNumber, int pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add(new transaction("Withdrawal", amount));
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new transaction("Deposit", amount));
        }
    }
    

    public boolean verifyPin(int pin) {
        return this.pin == pin;
    }

    public void setPin(int newPin) {
        this.pin = newPin;
    }

    public ArrayList<transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getPin() {
        return pin;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setTransactionHistory(ArrayList<transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }
}
