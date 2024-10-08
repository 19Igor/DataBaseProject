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

public class Actors implements Table {
    final String request = "select FIRST_NAME, SECOND_NAME, MIDDLE_NAME, HEIGHT, WEIGHT, VOICE_NAME, RANK_NAME\n" +
            "from ACTORS\n" +
            "join employees ee on id_employee = id_actor\n" +
            "join VOICES on ID_VOICE = TIMBRE\n" +
            "join RANKS on ID_RANK = RANK";
    final int rowCount = 7;

    @Override
    public void displayTable(TableView rightTable) throws SQLException {
        rightTable.getColumns().clear();

        ObservableList<TableColumns> strings = FXCollections.observableArrayList();
        TableData data = new TableData(request, rowCount);
        ArrayList<String> tableData = data.getTableData();

        for (int i = 0; i < tableData.size() - rowCount + 1; i += rowCount){
            strings.add(new TableColumns(tableData.get(i), tableData.get(i + 1), tableData.get(i + 2),
                    Integer.parseInt(tableData.get(i + 3)), Integer.parseInt(tableData.get(i + 4)),
                    tableData.get(i + 5), tableData.get(i + 6) ));
        }

        TableColumn<TableColumns, String> firstName = new TableColumn<>("First name");
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        rightTable.getColumns().add(firstName);

        TableColumn<TableColumns, String> secondName = new TableColumn<>("Second name");
        secondName.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        rightTable.getColumns().add(secondName);

        TableColumn<TableColumns, String> middleName = new TableColumn<>("Middle name");
        middleName.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        rightTable.getColumns().add(middleName);

        TableColumn<TableColumns, Integer> height = new TableColumn<>("Height");
        height.setCellValueFactory(new PropertyValueFactory<>("height"));
        rightTable.getColumns().add(height);

        TableColumn<TableColumns, Integer> weight = new TableColumn<>("Weight");
        weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        rightTable.getColumns().add(weight);

        TableColumn<TableColumns, String> voice = new TableColumn<>("Timbre");
        voice.setCellValueFactory(new PropertyValueFactory<>("voice"));
        rightTable.getColumns().add(voice);

        TableColumn<TableColumns, String> rank = new TableColumn<>("Rank");
        rank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        rightTable.getColumns().add(rank);

        rightTable.setItems(strings);
    }

    public class TableColumns{
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty secondName;
        private final SimpleStringProperty middleName;
        private final SimpleIntegerProperty height;
        private final SimpleIntegerProperty weight;
        private final SimpleStringProperty voice;
        private final SimpleStringProperty rank;

        public TableColumns(String firstName, String secondName, String middleName, int height,
                            int weight, String voice, String rank) {
            this.firstName = new SimpleStringProperty(firstName);
            this.secondName = new SimpleStringProperty(secondName);
            this.middleName = new SimpleStringProperty(middleName);
            this.height = new SimpleIntegerProperty(height);
            this.weight = new SimpleIntegerProperty(weight);
            this.voice = new SimpleStringProperty(voice);
            this.rank = new SimpleStringProperty(rank);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public String getSecondName() {
            return secondName.get();
        }

        public String getMiddleName() {
            return middleName.get();
        }

        public int getHeight() {
            return height.get();
        }

        public int getWeight() {
            return weight.get();
        }

        public String getVoice() {
            return voice.get();
        }

        public String getRank() {
            return rank.get();
        }
    }





    @Override
    public void changeEntry() {

    }

    @Override
    public void deleteEntry() {

    }
}
