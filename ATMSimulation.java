import java.util.ArrayList;
import java.util.Scanner;

class ATM {
    // Class-level variables to store the current balance, PIN, and transaction history
    private double balance;
    private int pin;
    private ArrayList<String> transactionHistory;

    // Constructor to initialize the ATM machine with a default PIN and balance
    public ATM(int initialPIN, double initialBalance) {
        this.pin = initialPIN;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    // Function to check the account balance
    public void checkBalance() {
        System.out.println("Your current balance is: ₹" + balance);
        transactionHistory.add("Checked balance: ₹" + balance);
    }

    // Function to withdraw cash from the account
    public void withdrawCash(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance! Transaction failed.");
        } else if (amount <= 0) {
            System.out.println("Invalid amount! Please enter a valid amount.");
        } else {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully.");
            transactionHistory.add("Withdrawn: ₹" + amount);
        }
    }

    // Function to deposit cash into the account
    public void depositCash(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount! Please enter a valid amount.");
        } else {
            balance += amount;
            System.out.println("₹" + amount + " deposited successfully.");
            transactionHistory.add("Deposited: ₹" + amount);
        }
    }

    // Function to change the PIN
    public void changePIN(int oldPIN, int newPIN) {
        if (oldPIN == pin) {
            pin = newPIN;
            System.out.println("PIN changed successfully.");
            transactionHistory.add("PIN changed successfully.");
        } else {
            System.out.println("Incorrect old PIN! PIN change failed.");
        }
    }

    // Function to display transaction history
    public void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    // Function to validate the PIN before any transaction
    public boolean validatePIN(int enteredPIN) {
        return enteredPIN == pin;
    }
}

public class ATMSimulation {
    public static void main(String[] args) {
        // Initialize the ATM with a default PIN and balance
        ATM atm = new ATM(1234, 10000.0);
        Scanner scanner = new Scanner(System.in);

        // Main menu loop
        while (true) {
            System.out.println("\nATM Machine");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Cash");
            System.out.println("3. Deposit Cash");
            System.out.println("4. Change PIN");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            if (choice == 6) {
                System.out.println("Thank you for using the ATM. Goodbye!");
                break;
            }

            System.out.print("Enter your PIN: ");
            int enteredPIN = scanner.nextInt();

            if (!atm.validatePIN(enteredPIN)) {
                System.out.println("Invalid PIN! Please try again.");
                continue;
            }

            switch (choice) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter the amount to withdraw: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdrawCash(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter the amount to deposit: ₹");
                    double depositAmount = scanner.nextDouble();
                    atm.depositCash(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter your old PIN: ");
                    int oldPIN = scanner.nextInt();
                    System.out.print("Enter your new PIN: ");
                    int newPIN = scanner.nextInt();
                    atm.changePIN(oldPIN, newPIN);
                    break;
                case 5:
                    atm.showTransactionHistory();
                    break;
                default:
                    System.out.println("Invalid option! Please choose a valid option.");
                    break;
            }
        }

        scanner.close();
    }
}
