package com.nsu.App.Model.TableOutput;

import com.nsu.App.Controllers.TableData;

import java.sql.SQLException;
import java.util.ArrayList;

public class Voices {
    public static ArrayList<String> getRealData() throws SQLException {
         final String request = "select *\n" +
                "from VOICES";
         final int rowCount = 2;

        ArrayList<String> data;
        TableData tableData = new TableData(request, rowCount);
        data = tableData.getTableData();
        return data;
    }
}
