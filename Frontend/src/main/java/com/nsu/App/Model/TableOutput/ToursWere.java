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

public class ToursWere implements Table{
    final String request = "with tours as (\n" +
            "select ID_TOUR, START_DATE, END_DATE, tt.NAME_THEATER as START_PLACE, tt.ADRESS as START_ADDRESS,\n" +
            "            ff.NAME_THEATER as END_PLACE, ff.ADRESS as END_ADDRESS, gd.NAME AS PLAY_NAME\n" +
            "            from Touring\n" +
            "            join THEATERS tt on tt.ID_THEATER = START_PLACE\n" +
            "            join THEATERS ff on ff.ID_THEATER = END_PLACE\n" +
            "            join GENERAL_DATA_OF_PERFORMANCES gd on PERFORMANCE_ID = PLAY\n" +
            ")\n" +
            "\n" +
            "select ee.FIRST_NAME, ee.SECOND_NAME, ee.MIDDLE_NAME, START_DATE as tour_start, END_DATE as end_tour, START_PLACE, START_ADDRESS, END_PLACE, END_ADDRESS, PLAY_NAME\n" +
            "from TOURINGS_WERE tw\n" +
            "join tours t on t.ID_TOUR = tw.ID_TOUR\n" +
            "join employees ee on ee.ID_EMPLOYEE = tw.ID_EMPLOYEE";
    final int rowCount = 10;


    @Override
    public void displayTable(TableView rightTable) throws SQLException {
        rightTable.getColumns().clear();

        ObservableList<TableColumns> strings = FXCollections.observableArrayList();
        TableData data = new TableData(request, rowCount);
        ArrayList<String> tableData = data.getTableData();

        for (int i = 0; i < tableData.size() - rowCount + 1; i += rowCount){

            strings.add(new TableColumns(tableData.get(i), tableData.get(i + 1), tableData.get(i + 2),
                    tableData.get(i + 3), tableData.get(i + 4), tableData.get(i + 5),tableData.get(i + 6),
                    tableData.get(i + 7), tableData.get(i + 8), tableData.get(i + 9)));
        }

        TableColumn<TableColumns, String> fNmae = new TableColumn<>("First Name");
        fNmae.setCellValueFactory(new PropertyValueFactory<>("fNmae"));
        rightTable.getColumns().add(fNmae);

        TableColumn<TableColumns, String> sName = new TableColumn<>("Second Name");
        sName.setCellValueFactory(new PropertyValueFactory<>("sName"));
        rightTable.getColumns().add(sName);

        TableColumn<TableColumns, String> mName = new TableColumn<>("Middle Name");
        mName.setCellValueFactory(new PropertyValueFactory<>("mName"));
        rightTable.getColumns().add(mName);

        TableColumn<TableColumns, String> tourStart = new TableColumn<>("Start of tour");
        tourStart.setCellValueFactory(new PropertyValueFactory<>("tourStart"));
        rightTable.getColumns().add(tourStart);

        TableColumn<TableColumns, String> tourEnd = new TableColumn<>("End of tour");
        tourEnd.setCellValueFactory(new PropertyValueFactory<>("tourEnd"));
        rightTable.getColumns().add(tourEnd);

        TableColumn<TableColumns, String> startPlace = new TableColumn<>("Start place");
        startPlace.setCellValueFactory(new PropertyValueFactory<>("startPlace"));
        rightTable.getColumns().add(startPlace);

        TableColumn<TableColumns, String> startAddress = new TableColumn<>("Start address");
        startAddress.setCellValueFactory(new PropertyValueFactory<>("startAddress"));
        rightTable.getColumns().add(startAddress);

        TableColumn<TableColumns, String> endPlace = new TableColumn<>("End place");
        endPlace.setCellValueFactory(new PropertyValueFactory<>("endPlace"));
        rightTable.getColumns().add(endPlace);

        TableColumn<TableColumns, String> endAddress = new TableColumn<>("End address");
        endAddress.setCellValueFactory(new PropertyValueFactory<>("endAddress"));
        rightTable.getColumns().add(endAddress);

        TableColumn<TableColumns, String> playName = new TableColumn<>("Name of play");
        playName.setCellValueFactory(new PropertyValueFactory<>("playName"));
        rightTable.getColumns().add(playName);

        rightTable.setItems(strings);
    }

    public class TableColumns{
        private final SimpleStringProperty fNmae;
        private final SimpleStringProperty sName;
        private final SimpleStringProperty mName;
        private final SimpleStringProperty tourStart;
        private final SimpleStringProperty tourEnd;
        private final SimpleStringProperty startPlace;
        private final SimpleStringProperty startAddress;
        private final SimpleStringProperty endPlace;
        private final SimpleStringProperty endAddress;
        private final SimpleStringProperty playName;

        public TableColumns(String fNmae, String sName, String mName, String tourStart, String tourEnd,
                            String startPlace, String startAddress, String endPlace, String endAddress, String playName){
            this.fNmae = new SimpleStringProperty(fNmae);
            this.sName = new SimpleStringProperty(sName);
            this.mName = new SimpleStringProperty(mName);
            this.tourStart = new SimpleStringProperty(tourStart);
            this.tourEnd = new SimpleStringProperty(tourEnd);
            this.startPlace = new SimpleStringProperty(startPlace);
            this.startAddress = new SimpleStringProperty(startAddress);
            this.endPlace = new SimpleStringProperty(endPlace);
            this.endAddress = new SimpleStringProperty(endAddress);
            this.playName = new SimpleStringProperty(playName);
        }

        public String getFNmae() {
            return fNmae.get();
        }

        public String getSName() {
            return sName.get();
        }

        public String getMName() {
            return mName.get();
        }

        public String getTourStart() {
            return tourStart.get();
        }

        public String getTourEnd() {
            return tourEnd.get();
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
