import model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.UserDatabase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserDatabaseTest {
    private static UserDatabase userDatabase;

    @BeforeAll
    static void beforeAll() {
        userDatabase = new UserDatabase();
    }

    @Test
    public void testGetUser_UserExists() {
        User user = new User("admin", "adminpass");
        User foundUser = UserDatabase.getUser("admin");

        assertNotNull(foundUser);
        assertEquals(user.getUsername(), foundUser.getUsername());
    }

    @Test
    public void testAddUser() {
        User newUser = new User("newUser", "newPass");
        UserDatabase.addUser(newUser);

        User foundUser = UserDatabase.getUser("newUser");
        assertNotNull(foundUser);
        assertEquals(newUser.getUsername(), foundUser.getUsername());
    }
}
