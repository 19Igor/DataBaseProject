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

public class Tours implements Table{
    final String request = "select START_DATE, END_DATE, tt.NAME_THEATER as START_PLACE, tt.ADRESS as START_ADDRESS, " +
            "ff.NAME_THEATER as END_PLACE, ff.ADRESS as END_ADDRESS, gd.NAME AS PLAY_NAME\n" +
            "from Touring\n" +
            "join THEATERS tt on tt.ID_THEATER = START_PLACE\n" +
            "join THEATERS ff on ff.ID_THEATER = END_PLACE\n" +
            "join GENERAL_DATA_OF_PERFORMANCES gd on PERFORMANCE_ID = PLAY";

    final int rowCount = 7;

    @Override
    public void displayTable(TableView rightTable) throws SQLException {
        rightTable.getColumns().clear();

        ObservableList<TableColumns> strings = FXCollections.observableArrayList();
        TableData data = new TableData(request, rowCount);
        ArrayList<String> tableData = data.getTableData();

        for (int i = 0; i < tableData.size() - rowCount + 1; i += rowCount){

            strings.add(new TableColumns(tableData.get(i), tableData.get(i + 1), tableData.get(i + 2),
                    tableData.get(i + 3), tableData.get(i + 4), tableData.get(i + 5), tableData.get(i + 6)));
        }

        TableColumn<Employees.TableColumns, String> startDate = new TableColumn<>("Date of start");
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        rightTable.getColumns().add(startDate);

        TableColumn<Employees.TableColumns, String> endtDate = new TableColumn<>("Date of end");
        endtDate.setCellValueFactory(new PropertyValueFactory<>("endtDate"));
        rightTable.getColumns().add(endtDate);

        TableColumn<Employees.TableColumns, String> startPlace = new TableColumn<>("Place of start");
        startPlace.setCellValueFactory(new PropertyValueFactory<>("startPlace"));
        rightTable.getColumns().add(startPlace);

        TableColumn<Employees.TableColumns, String> startAddress = new TableColumn<>("Address of start");
        startAddress.setCellValueFactory(new PropertyValueFactory<>("startAddress"));
        rightTable.getColumns().add(startAddress);

        TableColumn<Employees.TableColumns, String> endPlace = new TableColumn<>("Place of end");
        endPlace.setCellValueFactory(new PropertyValueFactory<>("endPlace"));
        rightTable.getColumns().add(endPlace);

        TableColumn<Employees.TableColumns, String> endAddress = new TableColumn<>("Address of end");
        endAddress.setCellValueFactory(new PropertyValueFactory<>("endAddress"));
        rightTable.getColumns().add(endAddress);

        TableColumn<Employees.TableColumns, String> playName = new TableColumn<>("Play");
        playName.setCellValueFactory(new PropertyValueFactory<>("playName"));
        rightTable.getColumns().add(playName);

        rightTable.setItems(strings);
    }

    public class TableColumns{
        private final SimpleStringProperty startDate;
        private final SimpleStringProperty endtDate;
        private final SimpleStringProperty startPlace;
        private final SimpleStringProperty startAddress;
        private final SimpleStringProperty endPlace;
        private final SimpleStringProperty endAddress;
        private final SimpleStringProperty playName;

        public TableColumns(String startDate, String endtDate, String startPlace, String startAddress,
                            String endPlace, String endAddress, String playName) {
            this.startDate      = new SimpleStringProperty(startDate);
            this.endtDate       = new SimpleStringProperty(endtDate);
            this.startPlace     = new SimpleStringProperty(startPlace);
            this.startAddress   = new SimpleStringProperty(startAddress);
            this.endPlace       = new SimpleStringProperty(endPlace);
            this.endAddress     = new SimpleStringProperty(endAddress);
            this.playName       = new SimpleStringProperty(playName);
        }

        public String getStartDate() {
            return startDate.get();
        }

        public String getEndtDate() {
            return endtDate.get();
        }

        public String getStartPlace() {
            return startPlace.get();
        }

        public String getStartAddress() {
            return startAddress.get();
        }

        public String getEndPlace() {
            return endPlace.get();
        }

        public String getEndAddress() {
            return endAddress.get();
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
