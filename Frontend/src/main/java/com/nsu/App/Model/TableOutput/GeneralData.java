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

public class GeneralData implements Table {
    final String request = "select gd.NAME, GENRE_NAME, t.name, a.NAME, a.SURNAME, DATE_OF_WRITTEN\n" +
            "from GENERAL_DATA_OF_PERFORMANCES gd\n" +
            "join GENRES g on g.ID_GENRE =  gd.GENRE\n" +
            "join types t on gd.type = t.ID_TYPE\n" +
            "join authors a on gd.AUTHOR = a.ID_AUTHOR";

    static int rowCount = 6;

    @Override
    public void displayTable(TableView rightTable) throws SQLException {
        rightTable.getColumns().clear();

        ObservableList<TableColumns> strings = FXCollections.observableArrayList();
        TableData data = new TableData(request, rowCount);
        ArrayList<String> tableData = data.getTableData();

        for (int i = 0; i < tableData.size() - rowCount + 1; i += rowCount){
            strings.add(new TableColumns(tableData.get(i), tableData.get(i + 1), tableData.get(i + 2),
                    tableData.get(i + 3), tableData.get(i + 4), tableData.get(i + 5)));
        }

        TableColumn<TableColumns, String> playName = new TableColumn<>("Name of play");
        playName.setCellValueFactory(new PropertyValueFactory<>("playName"));
        rightTable.getColumns().add(playName);

        TableColumn<Employees.TableColumns, String> genre = new TableColumn<>("Genre");
        genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        rightTable.getColumns().add(genre);

        TableColumn<TableColumns, String> type = new TableColumn<>("Type");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        rightTable.getColumns().add(type);

        TableColumn<TableColumns, String> fName = new TableColumn<>("Author name");
        fName.setCellValueFactory(new PropertyValueFactory<>("fName"));
        rightTable.getColumns().add(fName);

        TableColumn<TableColumns, String> sName = new TableColumn<>("Author surname");
        sName.setCellValueFactory(new PropertyValueFactory<>("sName"));
        rightTable.getColumns().add(sName);

        TableColumn<TableColumns, String> date = new TableColumn<>("Date of written");
        date.setCellValueFactory(new PropertyValueFactory<>("dateOfWritten"));
        rightTable.getColumns().add(date);

        rightTable.setItems(strings);

    }

    public class TableColumns{
        private final SimpleStringProperty playName;
        private final SimpleStringProperty genre;
        private final SimpleStringProperty type;
        private final SimpleStringProperty fName;
        private final SimpleStringProperty sName;
        private final SimpleStringProperty dateOfWritten;

        public TableColumns(String playName, String genre, String type, String fName,
                            String sName, String dateOfWritten) {
            this.playName       = new SimpleStringProperty(playName);
            this.genre          = new SimpleStringProperty(genre);
            this.type           = new SimpleStringProperty(type);
            this.fName          = new SimpleStringProperty(fName);
            this.sName          = new SimpleStringProperty(sName);
            this.dateOfWritten  = new SimpleStringProperty(dateOfWritten);
        }

        public String getPlayName() {
            return playName.get();
        }

        public String getGenre() {
            return genre.get();
        }

        public String getType() {
            return type.get();
        }

        public String getFName() {
            return fName.get();
        }

        public String getSName() {
            return sName.get();
        }

        public String getDateOfWritten() {
            return dateOfWritten.get();
        }
    }

    public static ArrayList<String> getRealData() throws SQLException {
        final String request = "select *\n" +
                "from GENERAL_DATA_OF_PERFORMANCES";
        final int rowCount = 6;

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
