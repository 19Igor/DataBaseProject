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

public class DatesAndPlays implements Table {

    final String request = "select gp.NAME, t4.PERFORMANCE_DATE\n" +
                            "from INTERMEDIATE_T4 t4\n" +
                            "join GENERAL_DATA_OF_PERFORMANCES gp on (gp.PERFORMANCE_ID = t4.ID_PERFORMANCE)";
    final int rowCount = 2;


    @Override
    public void displayTable(TableView rightTable) throws SQLException {

        rightTable.getColumns().clear();

        ObservableList<TableColumns> strings = FXCollections.observableArrayList();
        TableData data = new TableData(request, rowCount);
        ArrayList<String> tableData = data.getTableData();

        for (int i = 0; i < tableData.size() - rowCount + 1; i += rowCount){
            strings.add( new TableColumns( tableData.get(i), tableData.get(i + 1) ) );
        }

        TableColumn<TableColumns, String> name = new TableColumn<>("Name of a play");
        name.setCellValueFactory(new PropertyValueFactory<>("playName"));
        rightTable.getColumns().add(name);

        TableColumn<TableColumns, String> date = new TableColumn<>("Date of play");
        date.setCellValueFactory(new PropertyValueFactory<>("playDate"));
        rightTable.getColumns().add(date);

        rightTable.setItems(strings);

    }


    public class TableColumns{
        private final SimpleStringProperty playName;
        private final SimpleStringProperty playDate;

        public TableColumns(String playName, String playDate) {
            this.playName = new SimpleStringProperty(playName);
            this.playDate = new SimpleStringProperty(playDate);
        }

        public String getPlayName() {
            return playName.get();
        }

        public String getPlayDate() {
            return playDate.get();
        }

        public void setPlayName(String playName) {
            this.playName.set(playName);
        }

        public void setPlayDate(String playDate) {
            this.playDate.set(playDate);
        }
    }

    public static ArrayList<String> getRealData() throws SQLException {
        final String request = "select *\n" +
                "from INTERMEDIATE_T4";
        final int rowCount = 2;

        ArrayList<String> data;
        TableData tableData = new TableData(request, rowCount);
        data = tableData.getTableData();
        return data;
    }

    @Override
    public void changeEntry() {

    }

    @Override
    public void deleteEntry() {

    }
}
