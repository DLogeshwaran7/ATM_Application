import java.util.Scanner;

class ATM {

    private int pin = 1234;          // Default PIN
    private double balance = 5000;  // Initial Balance

    private String[] miniStatement = new String[5];
    private int txCount = 0;

    Scanner sc = new Scanner(System.in);

    // ---------------- LOGIN ----------------

    public void login() {

        System.out.print("Enter PIN: ");
        int enteredPin = sc.nextInt();

        if (enteredPin == pin) {
            System.out.println("Login Successful!");
            menu();
        } else {
            System.out.println("Wrong PIN!");
        }
    }

    // ---------------- MENU ----------------

    public void menu() {

        int choice;

        do {

            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Mini Statement");
            System.out.println("5. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    checkBalance();
                    break;

                case 2:
                    deposit();
                    break;

                case 3:
                    withdraw();
                    break;

                case 4:
                    showMiniStatement();
                    break;

                case 5:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 5);
    }

    // ---------------- BALANCE ----------------

    public void checkBalance() {

        System.out.println("Current Balance: ₹" + balance);
    }

    // ---------------- DEPOSIT ----------------

    public void deposit() {

        System.out.print("Enter Deposit Amount: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid Amount!");
            return;
        }

        balance += amount;

        addTransaction("Deposited: ₹" + amount);

        System.out.println("Deposit Successful!");
    }

    // ---------------- WITHDRAW ----------------

    public void withdraw() {

        System.out.print("Enter Withdrawal Amount: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid Amount!");
            return;
        }

        if (amount > balance) {
            System.out.println("Insufficient Balance!");
            return;
        }

        balance -= amount;

        addTransaction("Withdrawn: ₹" + amount);

        System.out.println("Please Collect Your Cash!");
    }

    // ---------------- MINI STATEMENT ----------------

    public void showMiniStatement() {

        if (txCount == 0) {
            System.out.println("No Transactions Yet!");
            return;
        }

        System.out.println("\n--- Mini Statement ---");

        for (int i = 0; i < txCount; i++) {
            System.out.println(miniStatement[i]);
        }
    }

    // ---------------- TRANSACTION HISTORY ----------------

    private void addTransaction(String data) {

        if (txCount < 5) {

            miniStatement[txCount++] = data;

        } else {

            // Shift left (FIFO)
            for (int i = 1; i < 5; i++) {
                miniStatement[i - 1] = miniStatement[i];
            }

            miniStatement[4] = data;
        }
    }
}
