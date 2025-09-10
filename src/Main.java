// Custom Exception for insufficient funds
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class BankAccount {
    private String accountHolder;
    private int accountNumber;
    private double balance;

    // Constructor
    public BankAccount(String accountHolder, int accountNumber, double initialBalance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    // Deposit
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amount;
        System.out.println(amount + " deposited successfully into " + accountHolder + "'s account.");
    }

    // Withdraw
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient balance in account: " + accountNumber);
        }
        balance -= amount;
        System.out.println(amount + " withdrawn successfully from " + accountHolder + "'s account.");
    }

    // Transfer
    public void transfer(BankAccount target, double amount) throws InsufficientFundsException {
        this.withdraw(amount); // may throw exception
        target.deposit(amount);
        System.out.println(amount + " transferred from " + this.accountHolder + " to " + target.accountHolder);
    }

    // Display balance
    public void displayBalance() {
        System.out.println("Account Holder: " + accountHolder + ", Account Number: " + accountNumber + ", Balance: " + balance);
    }
}
public class Main {
    public static void main(String[] args) {
        // Create accounts
        BankAccount acc1 = new BankAccount("vishu", 101, 1000);
        BankAccount acc2 = new BankAccount("vikas", 102, 500);

        // Show initial balances
        acc1.displayBalance();
        acc2.displayBalance();

        System.out.println("----- Transactions -----");

        // Deposit
        acc1.deposit(200);

        // Withdraw
        try {
            acc1.withdraw(500);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }

        // Transfer
        try {
            acc1.transfer(acc2, 300);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }

        // Final balances
        System.out.println("----- Final Balances -----");
        acc1.displayBalance();
        acc2.displayBalance();
    }
}

