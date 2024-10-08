package com.nsu.App.Model.TableOutput;

import com.nsu.App.Controllers.TableData;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;

public class SpecifiedData implements Table{

    final String request = "select gd.NAME, sd.BASE_TICKET_PRICE, ee.FIRST_NAME as Director_Name, " +
            "ee.SECOND_NAME as Director_Surname, aa.FIRST_NAME as ArtistDir_Name, aa.SECOND_NAME as ArtistDir_Surname, " +
            "ss.FIRST_NAME as Musicians_Name, ss.SECOND_NAME as MusDir_Surname\n" +
            "from SPECIFIC_DATA_OF_PERFORMANCES sd\n" +
            "join GENERAL_DATA_OF_PERFORMANCES gd on gd.PERFORMANCE_ID = sd.ID_PERFORMANCE\n" +
            "join employees  ee on DIRECTOR_DIRECTOR = ee.ID_EMPLOYEE\n" +
            "join employees aa on PAINTER_DIRECTOR = aa.ID_EMPLOYEE\n" +
            "join employees ss on MUSICAL_DIRECTOR = ss.ID_EMPLOYEE";

    final int rowCount = 8;

    @Override
    public void displayTable(TableView rightTable) throws SQLException {
        rightTable.getColumns().clear();

        ObservableList<TableColumns> strings = FXCollections.observableArrayList();
        TableData data = new TableData(request, rowCount);
        ArrayList<String> tableData = data.getTableData();

        for (int i = 0; i < tableData.size() - rowCount + 1; i += rowCount){

            strings.add(new TableColumns(tableData.get(i), Integer.parseInt(tableData.get(i + 1)),
                    tableData.get(i + 2), tableData.get(i + 3), tableData.get(i + 4), tableData.get(i + 5),
                    tableData.get(i + 6), tableData.get(i + 7)));
        }

        TableColumn<TableColumns, String> playName = new TableColumn<>("Name of a play");
        playName.setCellValueFactory(new PropertyValueFactory<>("playName"));
        rightTable.getColumns().add(playName);

        TableColumn<TableColumns, Integer> price = new TableColumn<>("Base price");
        price.setCellValueFactory(new PropertyValueFactory<>("basePrice"));
        rightTable.getColumns().add(price);

        TableColumn<TableColumns, String> dirName = new TableColumn<>("Name of Director");
        dirName.setCellValueFactory(new PropertyValueFactory<>("dirName"));
        rightTable.getColumns().add(dirName);

        TableColumn<TableColumns, String> dirSurname = new TableColumn<>("Surname of Director");
        dirSurname.setCellValueFactory(new PropertyValueFactory<>("dirSurname"));
        rightTable.getColumns().add(dirSurname);

        TableColumn<TableColumns, String> artDirName = new TableColumn<>("Name of ArtDirector");
        artDirName.setCellValueFactory(new PropertyValueFactory<>("artDirName"));
        rightTable.getColumns().add(artDirName);

        TableColumn<TableColumns, String> artDirSurname = new TableColumn<>("Surname of ArtDirector");
        artDirSurname.setCellValueFactory(new PropertyValueFactory<>("artDirSurname"));
        rightTable.getColumns().add(artDirSurname);

        TableColumn<TableColumns, String> musName = new TableColumn<>("Name of MusDirector");
        musName.setCellValueFactory(new PropertyValueFactory<>("musName"));
        rightTable.getColumns().add(musName);

        TableColumn<TableColumns, String> musSurname = new TableColumn<>("Surname of MusDirector");
        musSurname.setCellValueFactory(new PropertyValueFactory<>("musSurname"));
        rightTable.getColumns().add(musSurname);

        rightTable.setItems(strings);
    }

    public class TableColumns{
        private final SimpleStringProperty playName;
        private final SimpleIntegerProperty basePrice;
        private final SimpleStringProperty dirName;
        private final SimpleStringProperty dirSurname;
        private final SimpleStringProperty artDirName;
        private final SimpleStringProperty artDirSurname;
        private final SimpleStringProperty musName;
        private final SimpleStringProperty musSurname;

        public TableColumns(String playName, int basePrice, String dirName, String dirSurname, String artDirName,
                            String artDirSurname, String musName, String musSurname) {
            this.playName = new SimpleStringProperty(playName);
            this.basePrice = new SimpleIntegerProperty(basePrice);
            this.dirName = new SimpleStringProperty(dirName);
            this.dirSurname = new SimpleStringProperty(dirSurname);
            this.artDirName = new SimpleStringProperty(artDirName);
            this.artDirSurname = new SimpleStringProperty(artDirSurname);
            this.musName = new SimpleStringProperty(musName);
            this.musSurname = new SimpleStringProperty(musSurname);
        }

        public String getPlayName() {
            return playName.get();
        }

        public int getBasePrice() {
            return basePrice.get();
        }

        public String getDirName() {
            return dirName.get();
        }

        public String getDirSurname() {
            return dirSurname.get();
        }

        public String getArtDirName() {
            return artDirName.get();
        }

        public String getArtDirSurname() {
            return artDirSurname.get();
        }

        public String getMusName() {
            return musName.get();
        }

        public String getMusSurname() {
            return musSurname.get();
        }
    }

    @Override
    public void changeEntry() {

    }

    @Override
    public void deleteEntry() {

    }
}
