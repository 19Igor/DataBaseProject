package com.nsu.App.Model.TableOutput;


import com.nsu.App.Controllers.TableData;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Table {
    final String URL        = "jdbc:oracle:thin:@" + "84.237.50.81:1521" + ":";
    final String USER_NAME  = "20211_EPOV";
    final String PASS       = "20211_EPOV";
    String realRequest = new String();
    int rowCount = 0;

    void displayTable(TableView tableView) throws SQLException;
    void changeEntry();
    void deleteEntry();
}
