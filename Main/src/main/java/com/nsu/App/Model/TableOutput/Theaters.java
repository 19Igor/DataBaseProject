package com.nsu.App.Model.TableOutput;

import com.nsu.App.Controllers.TableData;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;

public class Theaters implements Table {

    final String request = "select NAME_THEATER, ADRESS\n" +
                            "from theaters\n";

    final int rowCount = 2;

    @Override
    public void displayTable(TableView rightTable) throws SQLException {
        rightTable.getColumns().clear();

        ObservableList<TableColumns> strings = FXCollections.observableArrayList();
        TableData data = new TableData(request, rowCount);
        ArrayList<String> tableData = data.getTableData();

        for (int i = 0; i < tableData.size() - rowCount + 1; i += rowCount){
            strings.add(new TableColumns(tableData.get(i) ,tableData.get(i + 1)));
        }

        TableColumn<TableColumns, String> name = new TableColumn<>("Theater name");
        name.setCellValueFactory(new PropertyValueFactory<>("theaterName"));
        rightTable.getColumns().add(name);

        TableColumn<TableColumns, String> address = new TableColumn<>("Theater address");
        address.setCellValueFactory(new PropertyValueFactory<>("theaterAddress"));
        rightTable.getColumns().add(address);

        rightTable.setItems(strings);

    }

    public class TableColumns{
        private final SimpleStringProperty theaterName;
        private final SimpleStringProperty theaterAddress;

        public TableColumns(String theaterName, String theaterAddress) {
            this.theaterName = new SimpleStringProperty(theaterName);
            this.theaterAddress = new SimpleStringProperty(theaterAddress);
        }

        public String getTheaterName() {
            return theaterName.get();
        }

        public String getTheaterAddress() {
            return theaterAddress.get();
        }
    }

    @Override
    public void changeEntry() {

    }

    @Override
    public void deleteEntry() {

    }
}
