package com.kirillsheremet.task1;

import java.sql.*;
import java.util.List;
import java.util.Properties;

public class DAO {


    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    public Connection getCon() {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "admin");
        properties.setProperty("useUnicode", "true");
        properties.setProperty("characterEncoding", "utf8");
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3307/b1_first_task_schema?characterEncoding=utf8", properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("OK");
        return con;
    }

    public void insertIntoDB(List<String[]> linesFromFile) throws SQLException {

        int countOfImportedStrings = 0;


        con = getCon();

        st = con.createStatement();

        for (String[] line : linesFromFile) {

            String sql = "INSERT b1_first_task_schema.values_from_file(Date_for_last_5_years,SymbolsEN,SymbolsRU,IntegerNumber,FloatNumber) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, line[0]);
            preparedStatement.setString(2, line[1]);
            preparedStatement.setString(3, line[2]);
            preparedStatement.setString(4, line[3]);
            preparedStatement.setString(5, line[4]);
            int rows = preparedStatement.executeUpdate();
            countOfImportedStrings++;
            if (countOfImportedStrings % 1000 == 0) {
                System.out.println("Imported string: " + countOfImportedStrings);
                System.out.println("left to import: " + (linesFromFile.size() - countOfImportedStrings));
            }

        }


    }

    public long getSumIntegerNumberFromDatabase() throws SQLException {
        con = getCon();

        st = con.createStatement();
        ResultSet resultSet = st.executeQuery("SELECT SUM(IntegerNumber) FROM b1_first_task_schema.values_from_file");
        long sum = 0;
        if (resultSet.next()) {
            sum = resultSet.getLong(1);
        }
        return sum;
    }

    public double getMedianFloatNumberFromDatabase() throws SQLException {
        con = getCon();

        st = con.createStatement();
        ResultSet resultSet = st.executeQuery("WITH Numbered AS \n" +
                "(\n" +
                "SELECT *, COUNT(*) OVER () AS TotatRecords,\n" +
                "    ROW_NUMBER() OVER (ORDER BY FloatNumber) AS RowNum\n" +
                "FROM b1_first_task_schema.values_from_file\n" +
                ")\n" +
                "SELECT Avg(FloatNumber)\n" +
                "FROM Numbered\n" +
                "WHERE RowNum IN ( FLOOR((TotatRecords+1)/2), FLOOR((TotatRecords+2)/2) )\n" +
                ";");
        double med = 0;
        if (resultSet.next()) {
            med = resultSet.getDouble(1);
        }
        return med;
    }

}
