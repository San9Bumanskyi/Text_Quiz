import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserConstructorAndGetters() {
        String username = "testuser";
        String password = "password123";

        User user = new User(username, password);

        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void testSetters() {
        User user = new User("oldUsername", "oldPassword");

        user.setUsername("newUsername");
        user.setPassword("newPassword");

        assertEquals("newUsername", user.getUsername());
        assertEquals("newPassword", user.getPassword());
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSerialization() throws Exception {
        User user = new User("testuser", "password123");

        String json = objectMapper.writeValueAsString(user);

        assertNotNull(json);
        assertTrue(json.contains("testuser"));
        assertTrue(json.contains("password123"));
    }

    @Test
    public void testDeserialization() throws Exception {
        String json = "{\"username\":\"testuser\",\"password\":\"password123\"}";

        User user = objectMapper.readValue(json, User.class);

        assertNotNull(user);
        assertEquals("testuser", user.getUsername());
        assertEquals("password123", user.getPassword());
    }
}