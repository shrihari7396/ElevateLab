import java.util.Scanner;

public class ConsoleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println("      Welcome to Java Calculator     ");
        System.out.println("====================================");

        boolean continueCalculating = true;

        while (continueCalculating) {
            System.out.println("\nSelect an operation to perform:");
            System.out.println("1. Addition (+)");
            System.out.println("2. Subtraction (-)");
            System.out.println("3. Multiplication (*)");
            System.out.println("4. Division (/)");
            System.out.println("5. Exit");

            System.out.print("Enter your choice (1-5): ");
            int choice = scanner.nextInt();

            if (choice == 5) {
                System.out.println("Thank you for using the calculator. Goodbye!");
                break;
            }

            System.out.print("Enter the first number: ");
            double num1 = scanner.nextDouble();

            System.out.print("Enter the second number: ");
            double num2 = scanner.nextDouble();

            double result = 0;
            boolean validChoice = true;

            switch (choice) {
                case 1:
                    result = num1 + num2;
                    System.out.println("Result: " + num1 + " + " + num2 + " = " + result);
                    break;
                case 2:
                    result = num1 - num2;
                    System.out.println("Result: " + num1 + " - " + num2 + " = " + result);
                    break;
                case 3:
                    result = num1 * num2;
                    System.out.println("Result: " + num1 + " * " + num2 + " = " + result);
                    break;
                case 4:
                    if (num2 != 0) {
                        result = num1 / num2;
                        System.out.println("Result: " + num1 + " / " + num2 + " = " + result);
                    } else {
                        System.out.println("Error: Cannot divide by zero.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please select a number between 1 and 5.");
                    validChoice = false;
                    break;
            }

            if (validChoice) {
                System.out.print("\nDo you want to perform another calculation? (yes/no): ");
                String userResponse = scanner.next().toLowerCase();
                if (!userResponse.equals("yes") && !userResponse.equals("y")) {
                    continueCalculating = false;
                    System.out.println("Thanks for using the calculator!");
                }
            }
        }

        scanner.close();
    }
}
