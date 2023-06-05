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

public class TheatersAndPlays implements Table{

    final String request = "select t.NAME_THEATER, t.ADRESS, gd.NAME\n" +
                            "from INTERMEDIATE_T2 t2\n" +
                            "join THEATERS t on t2.id_theater = t.ID_THEATER\n" +
                            "join GENERAL_DATA_OF_PERFORMANCES gd on t2.id_performance = gd.PERFORMANCE_ID";
    final int rowCount = 3;

    @Override
    public void displayTable(TableView rightTable) throws SQLException {
        rightTable.getColumns().clear();

        ObservableList<TableColumns> strings = FXCollections.observableArrayList();
        TableData data = new TableData(request, rowCount);
        ArrayList<String> tableData = data.getTableData();

        for (int i = 0; i < tableData.size() - rowCount + 1; i += rowCount){
            strings.add(new TableColumns(tableData.get(i), tableData.get(i + 1), tableData.get(i + 2)));
        }

        TableColumn<TableColumns, String> theaterName = new TableColumn<>("Theater name");
        theaterName.setCellValueFactory(new PropertyValueFactory<>("theaterName"));
        rightTable.getColumns().add(theaterName);

        TableColumn<TableColumns, String> address = new TableColumn<>("Address");
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        rightTable.getColumns().add(address);

        TableColumn<TableColumns, String> playName = new TableColumn<>("Name of a play");
        playName.setCellValueFactory(new PropertyValueFactory<>("playName"));
        rightTable.getColumns().add(playName);

        rightTable.setItems(strings);
    }

    public class TableColumns{
        private final SimpleStringProperty theaterName;
        private final SimpleStringProperty address;
        private final SimpleStringProperty playName;

        public TableColumns(String theaterName, String address, String playName) {
            this.theaterName = new SimpleStringProperty(theaterName);
            this.address = new SimpleStringProperty(address);
            this.playName = new SimpleStringProperty(playName);
        }

        public String getTheaterName() {
            return theaterName.get();
        }

        public String getAddress() {
            return address.get();
        }

        public String getPlayName() {
            return playName.get();
        }
    }

    @Override
    public void changeEntry() {

    }

    @Override
    public void deleteEntry() {

    }
}
