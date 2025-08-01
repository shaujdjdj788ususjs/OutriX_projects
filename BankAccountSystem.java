import java.util.Scanner;

class BankAccount {
    private String accountHolder;
    private double balance;

    public BankAccount(String name, double initialDeposit) {
        this.accountHolder = name;
        this.balance = initialDeposit;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("₹" + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
        } else {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully.");
        }
    }

    public void checkBalance() {
        System.out.println("Current balance: ₹" + balance);
    }

    public void showAccountInfo() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Balance: ₹" + balance);
    }
}

public class BankAccountSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Welcome to Java Bank ---");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter initial deposit amount: ₹");
        double initial = scanner.nextDouble();

        BankAccount account = new BankAccount(name, initial);

        int choice;
        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Account Info");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> account.checkBalance();
                case 2 -> {
                    System.out.print("Enter deposit amount: ₹");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                }
                case 3 -> {
                    System.out.print("Enter withdrawal amount: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                }
                case 4 -> account.showAccountInfo();
                case 5 -> System.out.println("Thank you for using Java Bank!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
