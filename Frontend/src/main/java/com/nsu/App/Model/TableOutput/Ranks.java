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

public class Ranks implements Table {
    final String request = "select NAME, COMPETITION_DATE\n" +
                            "from competitions";
    final int rowCount = 2;

    @Override
    public void displayTable(TableView rightTable) throws SQLException {
        rightTable.getColumns().clear();

        ObservableList<TableColumns> strings = FXCollections.observableArrayList();
        TableData data = new TableData(request, rowCount);
        ArrayList<String> tableData = data.getTableData();

        for (int i = 0; i < tableData.size() - rowCount + 1; i += rowCount){
            strings.add(new TableColumns(tableData.get(i), tableData.get(i + 1)));
        }

        TableColumn<TableColumns, String> name = new TableColumn<>("Name of competition");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        rightTable.getColumns().add(name);

        TableColumn<TableColumns, String> date = new TableColumn<>("Date of competition");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        rightTable.getColumns().add(date);

        rightTable.setItems(strings);
    }

    public class TableColumns{
        private final SimpleStringProperty name;
        private final SimpleStringProperty date;

        public TableColumns(String name, String date) {
            this.name = new SimpleStringProperty(name);
            this.date = new SimpleStringProperty(date);
        }

        public String getName() {
            return name.get();
        }

        public String getDate(){
            return date.get();
        }
    }


    @Override
    public void changeEntry() {

    }

    @Override
    public void deleteEntry() {

    }
}
