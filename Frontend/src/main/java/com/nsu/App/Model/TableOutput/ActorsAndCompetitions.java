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

public class ActorsAndCompetitions implements Table {
    private String request = "with full_actors as (\n" +
            "select id_actor, FIRST_NAME, SECOND_NAME, MIDDLE_NAME, HEIGHT, WEIGHT, VOICE_NAME, RANK_NAME\n" +
            "from ACTORS\n" +
            "join employees ee on id_employee = id_actor\n" +
            "join VOICES on ID_VOICE = TIMBRE\n" +
            "join RANKS on ID_RANK = RANK\n" +
            ")\n" +
            "\n" +
            "select FIRST_NAME, SECOND_NAME, MIDDLE_NAME, NAME, COMPETITION_DATE, RESULT\n" +
            "from INTERMEDIATE_T5 t5\n" +
            "join full_actors fa on fa.id_actor = t5.ID_ACTOR\n" +
            "join COMPETITIONS cc on cc.ID_COMPETITION = t5.ID_COMPETITION";

    private int rowCount = 6;

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

        TableColumn<Ranks.TableColumns, String> firstName = new TableColumn<>("First name");
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        rightTable.getColumns().add(firstName);

        TableColumn<Ranks.TableColumns, String> secondName = new TableColumn<>("Second name");
        secondName.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        rightTable.getColumns().add(secondName);

        TableColumn<Ranks.TableColumns, String> middleName = new TableColumn<>("Middle name");
        middleName.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        rightTable.getColumns().add(middleName);

        TableColumn<Ranks.TableColumns, String> compName = new TableColumn<>("Competition");
        compName.setCellValueFactory(new PropertyValueFactory<>("compName"));
        rightTable.getColumns().add(compName);

        TableColumn<Ranks.TableColumns, String> compDate = new TableColumn<>("Competition date");
        compDate.setCellValueFactory(new PropertyValueFactory<>("compDate"));
        rightTable.getColumns().add(compDate);

        TableColumn<Ranks.TableColumns, String> result = new TableColumn<>("Result");
        result.setCellValueFactory(new PropertyValueFactory<>("result"));
        rightTable.getColumns().add(result);

        rightTable.setItems(strings);

    }

    public class TableColumns{
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty secondName;
        private final SimpleStringProperty middleName;
        private final SimpleStringProperty compName;
        private final SimpleStringProperty compDate;
        private final SimpleStringProperty result;

        public TableColumns(String firstName, String secondName, String middleName, String compName,
                            String compDate, String result) {
            this.firstName = new SimpleStringProperty(firstName);
            this.secondName = new SimpleStringProperty(secondName);
            this.middleName = new SimpleStringProperty(middleName);
            this.compName = new SimpleStringProperty(compName);
            this.compDate = new SimpleStringProperty(compDate);
            this.result = new SimpleStringProperty(result);
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

        public String getCompName() {
            return compName.get();
        }

        public String getCompDate() {
            return compDate.get();
        }

        public String getResult() {
            return result.get();
        }
    }




    @Override
    public void changeEntry() {

    }

    @Override
    public void deleteEntry() {

    }
}
