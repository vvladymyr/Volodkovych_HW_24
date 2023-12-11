package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.testng.AssertJUnit.assertEquals;

public class MainTest {

        private static Connection connection;
        private static Statement statement;

        @BeforeAll
        public static void setUp() throws Exception {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres", "docker");
            statement = connection.createStatement();
        }

        @Test
        public void testDatabaseFunctionality() throws Exception {
            // Assuming you have a table named CAR with columns: Id, Column2, Column3
            String select_sql = "SELECT * FROM CAR";
            ResultSet rs = statement.executeQuery(select_sql);

            int[] expectedIds = {1, 2};  // Update with the expected values from your database

            int i = 0;
            while (rs.next()) {
                int actualId = rs.getInt("Id");
                assertEquals(expectedIds[i++], actualId);

                // Add additional assertions for other fields if needed
                // assertEquals(expectedValue, rs.getString("ColumnName"));

                // You can also test other conditions based on your requirements
            }
        }

        @AfterAll
        public static void tearDown() throws Exception {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

