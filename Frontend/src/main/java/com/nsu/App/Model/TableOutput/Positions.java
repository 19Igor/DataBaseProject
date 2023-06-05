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

public class Positions implements Table {

    final int rowCount = 2;
    final String request = "select *" +
                            "from position";


    @Override
    public void displayTable(TableView rightTable) throws SQLException {

        rightTable.getColumns().clear();

        ObservableList<TableColumns> strings = FXCollections.observableArrayList();
        TableData data = new TableData(request, rowCount);
        ArrayList<String> tableData = data.getTableData();

        for(int i = 0; i < tableData.size() - rowCount + 1; i += rowCount){
            strings.add(new TableColumns(tableData.get(i + 1)));
        }

        TableColumn<PlacesAtTheater.TableColumns, Integer> posName = new TableColumn<>("Position");
        posName.setCellValueFactory(new PropertyValueFactory<>("positionName"));
        rightTable.getColumns().add(posName);

        rightTable.setItems(strings);

    }


    public class TableColumns{
        private final SimpleStringProperty positionName;

        public TableColumns(String positionName) {
            this.positionName = new SimpleStringProperty(positionName);
        }

        public String getPositionName() {
            return positionName.get();
        }

        public void setPositionName(String positionName) {
            this.positionName.set(positionName);
        }
    }


    @Override
    public void changeEntry() {

    }

    @Override
    public void deleteEntry() {

    }
}
