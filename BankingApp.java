import java.util.*;

// Custom Exception for Banking Logic
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class Account {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private List<String> transactionHistory;

    public Account(String name, String accNum, double initialDeposit) {
        this.accountHolder = name;
        this.accountNumber = accNum;
        this.balance = initialDeposit;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account opened with: ₹" + initialDeposit);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: ₹" + amount);
            System.out.println("Successfully deposited ₹" + amount);
        }
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Error: Insufficient balance!");
        }
        balance -= amount;
        transactionHistory.add("Withdrew: ₹" + amount);
        System.out.println("Successfully withdrew ₹" + amount);
    }

    public void showHistory() {
        System.out.println("\n--- Transaction History for " + accountNumber + " ---");
        for (String record : transactionHistory) {
            System.out.println(record);
        }
        System.out.println("Current Balance: ₹" + balance);
    }
}

public class BankingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Using a Map to store multiple accounts
        Map<String, Account> bankData = new HashMap<>();

        while (true) {
            System.out.println("\n1. Create Account 2. Deposit 3. Withdraw 4. History 5. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Name: "); String name = sc.nextLine();
                        String accNum = "ACC" + (bankData.size() + 101);
                        System.out.print("Initial Deposit: "); double dep = sc.nextDouble();
                        bankData.put(accNum, new Account(name, accNum, dep));
                        System.out.println("Account Created! Your ID is: " + accNum);
                    }
                    case 2 -> {
                        System.out.print("Enter Account ID: "); String id = sc.next();
                        System.out.print("Amount: "); double amt = sc.nextDouble();
                        if (bankData.containsKey(id)) bankData.get(id).deposit(amt);
                        else System.out.println("Account not found.");
                    }
                    case 3 -> {
                        System.out.print("Enter Account ID: "); String id = sc.next();
                        System.out.print("Amount: "); double amt = sc.nextDouble();
                        if (bankData.containsKey(id)) bankData.get(id).withdraw(amt);
                        else System.out.println("Account not found.");
                    }
                    case 4 -> {
                        System.out.print("Enter Account ID: "); String id = sc.next();
                        if (bankData.containsKey(id)) bankData.get(id).showHistory();
                    }
                    case 5 -> System.exit(0);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
