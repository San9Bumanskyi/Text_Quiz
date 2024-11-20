package utils;

import model.User;
import java.util.HashMap;
import java.util.Map;

public class UserDatabase {
    private static Map<String, User> users = new HashMap<>();

    static {
        users.put("admin", new User("admin", "adminpass", "admin"));
        users.put("user", new User("user", "userpass", "user"));
        users.put("guest", new User("guest", "guestpass", "guest"));
    }

    public static User getUser(String username) {
        return users.get(username);
    }

    public static void addUser(User user) {
        users.put(user.getUsername(), user);
    }
}
