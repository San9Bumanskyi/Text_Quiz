package utils;

import model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserDatabase {
    private static final String USERS_FILE = "users.json";
    private static Map<String, User> users = new HashMap<>();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        try {
            File file = new File(USERS_FILE);
            System.out.println("File path: " + file.getAbsolutePath());
            if (file.exists()) {
                users = objectMapper.readValue(file, new TypeReference<Map<String, User>>() {});
            } else {
                users.put("admin", new User("admin", "adminpass"));
                users.put("user", new User("user", "userpass"));
                users.put("guest", new User("guest", "guestpass"));
                saveUsers();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User getUser(String username) {
        return users.get(username);
    }

    public static void addUser(User user) {
        users.put(user.getUsername(), user);
        saveUsers();
    }

    private static void saveUsers() {
        try {
            objectMapper.writeValue(new File(USERS_FILE), users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
