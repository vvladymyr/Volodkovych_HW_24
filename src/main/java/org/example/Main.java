package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        String select_sql = "SELECT * FROM CAR";

        // This will load the Postgres driver, each DB has its own driver
//        Class.forName("com.mysql.jdbc.Driver");
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres", "docker");
        Statement statement = connection.createStatement();


        int[] array = new int[3];
        int i = 0;
        ResultSet rs = statement.executeQuery(select_sql);
        while (rs.next()){
            System.out.println(rs.getString("Id") + " " + rs.getString("name") + " " + rs.getString("quantity"));
            int a = rs.getInt("Id");
            array[i++] = a;
        }

        connection.close();
    }

}
