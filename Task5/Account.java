import java.util.ArrayList;
import java.util.List;

public class Account {
    String accNo;
    String holderName;
    double balance;
    List<String> history = new ArrayList<>();

    Account(String accNo, String holderName, double balance) {
        this.accNo = accNo;
        this.holderName = holderName;
        this.balance = balance;
        history.add("Account opened with balance: ₹" + balance);
    }

    void deposit(double amt) {
        if (amt > 0) {
            balance += amt;
            history.add("Deposited: ₹" + amt + " | Balance: ₹" + balance);
            System.out.println("Amount deposited successfully");
        } else {
            System.out.println("Deposit amount must be positive");
        }
    }

    void withdraw(double amt) {
        if (amt > 0 && amt <= balance) {
            balance -= amt;
            history.add("Withdrew: ₹" + amt + " | Balance: ₹" + balance);
            System.out.println("Amount withdrawn successfully");
        } else if (amt > balance) {
            System.out.println("Not enough balance");
        } else {
            System.out.println("Invalid amount");
        }
    }

    void showBalance() {
        System.out.println("Current Balance: ₹" + balance);
    }

    void showHistory() {
        System.out.println("Transaction History:");
        for (String rec : history) {
            System.out.println(rec);
        }
    }
}