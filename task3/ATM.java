/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task3;

import java.util.Scanner;

/**
 *
 * @author himan
 */
public class ATM {
    private User currentUser;
    private Scanner scanner;

    public ATM() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the ATM");
        showInitialMenu();
    }

    private void showInitialMenu() {
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    authenticateUser();
                    return;
                case 2:
                    registerUser();
                    break;
                case 3:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void authenticateUser() {
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        currentUser = ATMSystem.authenticateUser(userId, pin);
        if (currentUser != null) {
            showMenu();
        } else {
            System.out.println("Invalid user ID or PIN.");
        }
    }

    private void registerUser() {
        System.out.print("Enter new user ID: ");
        String userId = scanner.nextLine();
        if (ATMSystem.userExists(userId)) {
            System.out.println("User ID already exists. Please try a different ID.");
            return;
        }
        System.out.print("Enter new PIN: ");
        String pin = scanner.nextLine();
        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        ATMSystem.registerUser(userId, pin, initialDeposit);
        System.out.println("User registered successfully. You can now login.");
    }

    private void showMenu() {
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    currentUser.getAccount().printTransactionHistory();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        if (currentUser.getAccount().withdraw(amount)) {
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        currentUser.getAccount().deposit(amount);
        System.out.println("Deposit successful.");
    }

    private void transfer() {
        System.out.print("Enter recipient user ID: ");
        String recipientId = scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        if (ATMSystem.transferFunds(currentUser.getUserId(), recipientId, amount)) {
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Transfer failed. Check recipient ID and funds.");
        }
    }
}