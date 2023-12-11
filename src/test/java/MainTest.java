import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testDatabase() throws Exception {

        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres", "docker");
        Statement statement = connection.createStatement();

        String select_sql = "SELECT * FROM CAR";
        ResultSet rs = statement.executeQuery(select_sql);

        assertNotNull(rs);

        int[] expectedArray = {1, 2, 3};

        int[] actualArray = new int[3];
        int i = 0;

        while (rs.next()) {
            int a = rs.getInt("id");
            actualArray[i++] = a;
            String name = rs.getString("name");
            int quantity = rs.getInt("quantity");

            assertNotNull(name);
            assertTrue(quantity >= 0);
        }

        assertArrayEquals(expectedArray, actualArray);

        connection.close();
    }
}