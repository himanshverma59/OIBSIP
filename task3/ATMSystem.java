/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task3;

/**
 *
 * @author himan
 */
import java.util.HashMap;
import java.util.Map;

public class ATMSystem {
    private static Map<String, User> users = new HashMap<>();

    public static User authenticateUser(String userId, String pin) {
        User user = users.get(userId);
        if (user != null && user.getPin().equals(pin)) {
            return user;
        }
        return null;
    }

    public static boolean userExists(String userId) {
        return users.containsKey(userId);
    }

    public static void registerUser(String userId, String pin, double initialDeposit) {
        Account account = new Account(initialDeposit);
        User user = new User(userId, pin, account);
        users.put(userId, user);
    }

    public static boolean transferFunds(String senderId, String recipientId, double amount) {
        User sender = users.get(senderId);
        User recipient = users.get(recipientId);
        if (sender != null && recipient != null && sender.getAccount().transfer(recipient.getAccount(), amount)) {
            return true;
        }
        return false;
    }
}