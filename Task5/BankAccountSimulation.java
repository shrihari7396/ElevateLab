import java.util.*;



public class BankAccountSimulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();
        System.out.print("Enter Holder Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Initial Balance: ₹");
        double bal = sc.nextDouble();

        Account acc = new Account(accNo, name, bal);

        int choice;
        do {
            System.out.println("\n1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ₹");
                    double damt = sc.nextDouble();
                    acc.deposit(damt);
                    break;

                case 2:
                    System.out.print("Enter withdrawal amount: ₹");
                    double wamt = sc.nextDouble();
                    acc.withdraw(wamt);
                    break;

                case 3:
                    acc.showBalance();
                    break;

                case 4:
                    acc.showHistory();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 5);

        sc.close();
    }
}
