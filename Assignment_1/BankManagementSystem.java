import java.util.*;

//  interface (deposit, withdraw, check, details) ---
interface Bank {
    void deposit();
    void withdraw();
    void checkBalance();
    void accountDetails();
}

// --- SRP: Account class  manages account-related logic ---
// --- OCP: Designed to allow extension through subclasses (Savings, Current) ---
abstract class Account implements Bank { //abstraction
    protected String accountNo;
    protected String holderName;
    protected long balance;
    protected Scanner sc = new Scanner(System.in);

    public Account(String accountNo, String holderName, long balance) {
        this.accountNo = accountNo;
        this.holderName = holderName;
        this.balance = balance;
    }

    @Override
    public void deposit() {
        System.out.print("Enter deposit amount: ");
        long money = sc.nextLong();
        if (money > 0) {
            balance += money;
            System.out.println("Deposit successful. Current balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    @Override
    public void checkBalance() {
        System.out.println(holderName + " (A/C: " + accountNo + ") Current Balance: " + balance);
    }

    @Override
    public void accountDetails() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Account Holder: " + holderName);
        System.out.println("Account Number: " + accountNo);
        System.out.println("Account Balance: " + balance);
    }
}

// --- LSP: Subclass can replace parent class without breaking functionality ---
class SavingsAccount extends Account {
    private static final long MIN_BALANCE = 500;

    public SavingsAccount(String accountNo, String holderName, long balance) {
        super(accountNo, holderName, balance);
    }

    @Override
    public void withdraw() {
        System.out.print("Enter withdrawal amount: ");
        long amount = sc.nextLong();
        if (balance - amount >= MIN_BALANCE) {
            balance -= amount;
            System.out.println("Withdrawal successful. Remaining balance: " + balance);
        } else {
            System.out.println("Withdrawal denied! Maintain minimum balance of " + MIN_BALANCE);
        }
    }
}

class CurrentAccount extends Account { //Inheritance
    private static final long OVERDRAFT_LIMIT = 1000;

    public CurrentAccount(String accountNo, String holderName, long balance) {
        super(accountNo, holderName, balance);
    }

    @Override
    public void withdraw() {
        System.out.print("Enter withdrawal amount: ");
        long amount = sc.nextLong();
        if (balance + OVERDRAFT_LIMIT >= amount) {
            balance -= amount;
            System.out.println("Withdrawal successful. Remaining balance: " + balance);
        } else {
            System.out.println("Overdraft limit exceeded! Withdrawal denied.");
        }
    }
}

// --- DIP: Main depends on abstraction (Bank) instead of concrete classes ---
public class BankManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // --- Account Creation ---
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter account number: ");
        String accNo = sc.nextLine();

        System.out.println("Choose Account Type: 1. Savings  2. Current");
        int type = sc.nextInt();

        Bank account; // Programming to interface (DIP)
        if (type == 1) {
            account = new SavingsAccount(accNo, name, 1000); // Initial balance
        } else {
            account = new CurrentAccount(accNo, name, 1000);
        }

        // --- Menu-driven Program ---
        while (true) {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Account Details");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> account.deposit();
                case 2 -> account.withdraw();
                case 3 -> account.checkBalance();
                case 4 -> account.accountDetails();
                case 5 -> {
                    System.out.println("Thank you, " + name + "! Exiting...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
