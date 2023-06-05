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

public class Roles implements Table {
    final String request = "select ROLE_NAME, HEIGHT, WEIGHT, VOICE_NAME, NAME\n" +
            "from roles r\n" +
            "join VOICES v on v.ID_VOICE = r.ID_ROLE\n" +
            "join GENERAL_DATA_OF_PERFORMANCES gd on gd.PERFORMANCE_ID = r.PERFORMANCE_INFORMATION";

    final int rowCount = 5;

    @Override
    public void displayTable(TableView rightTable) throws SQLException {
        rightTable.getColumns().clear();

        ObservableList<TableColumns> strings = FXCollections.observableArrayList();
        TableData data = new TableData(request, rowCount);
        ArrayList<String> tableData = data.getTableData();

        for (int i = 0; i < tableData.size() - rowCount + 1; i += rowCount){
            strings.add(new TableColumns(tableData.get(i),tableData.get(i + 1), tableData.get(i + 2),
                    tableData.get(i + 3), tableData.get(i + 4)));
        }

        TableColumn<TableColumns, String> role = new TableColumn<>("Role");
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        rightTable.getColumns().add(role);

        TableColumn<TableColumns, String> height = new TableColumn<>("Height");
        height.setCellValueFactory(new PropertyValueFactory<>("height"));
        rightTable.getColumns().add(height);

        TableColumn<TableColumns, String> weight = new TableColumn<>("Weight");
        weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        rightTable.getColumns().add(weight);

        TableColumn<TableColumns, String> voice = new TableColumn<>("Voice");
        voice.setCellValueFactory(new PropertyValueFactory<>("voice"));
        rightTable.getColumns().add(voice);

        TableColumn<TableColumns, String> play = new TableColumn<>("Play");
        play.setCellValueFactory(new PropertyValueFactory<>("play"));
        rightTable.getColumns().add(play);

        rightTable.setItems(strings);
    }

    public class TableColumns{
        private final SimpleStringProperty role;
        private final SimpleStringProperty height;
        private final SimpleStringProperty weight;
        private final SimpleStringProperty voice;
        private final SimpleStringProperty play;

        public TableColumns(String role, String height, String weight, String voice, String play) {
            this.role = new SimpleStringProperty(role);
            this.height = new SimpleStringProperty(height);
            this.weight = new SimpleStringProperty(weight);
            this.voice = new SimpleStringProperty(voice);
            this.play = new SimpleStringProperty(play);
        }

        public String getRole() {
            return role.get();
        }

        public String getHeight() {
            return height.get();
        }

        public String getWeight() {
            return weight.get();
        }

        public String getVoice() {
            return voice.get();
        }

        public String getPlay() {
            return play.get();
        }
    }

    @Override
    public void changeEntry() {

    }

    @Override
    public void deleteEntry() {

    }

    public static ArrayList<String> getRealData() throws SQLException {
        final String request = "select *\n" +
                "from ROLES";
        final int rowCount = 6;

        ArrayList<String> data;
        TableData tableData = new TableData(request, rowCount);
        data = tableData.getTableData();
        return data;
    }
}
