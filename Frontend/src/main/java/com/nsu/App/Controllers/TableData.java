package com.nsu.App.Controllers;

import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;

public class TableData {

    private String tableRequest = null;
    int columnNumber = 0;
    final private String URL        = "jdbc:oracle:thin:@" + "84.237.50.81:1521" + ":";
    final private String USER_NAME  = "20211_EPOV";
    final private String PASS       = "20211_EPOV";


    public TableData(String request, int columnNumber){
        this.tableRequest = request;
        this.columnNumber = columnNumber;
    }

    public ArrayList<String> getTableData() throws SQLException {

        ArrayList<String> line = new ArrayList<>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        Connection conn = null;
        Locale.setDefault(Locale.ENGLISH);

        try {
            conn = DriverManager.getConnection(URL, USER_NAME, PASS);

            if (conn == null) {
                System.out.println("Нет соединения с БД!");
                System.exit(0);
            }

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(tableRequest);

            while(rs.next())
                for(int k = 1; k < columnNumber + 1; k++) {
                    var s = rs.getString(k);
                    if (s == null){
                        line.add("--");
                    }else{
                        line.add(rs.getString(k).trim());
                    }
                }

        } catch (SQLException e) {
            System.out.println("Нет");
            e.printStackTrace();
        } finally{
            if (conn != null){
                conn.close();
            }
        }

        return line;
    }
}
