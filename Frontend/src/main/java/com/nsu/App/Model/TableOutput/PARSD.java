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

public class PARSD implements Table {
    final String request  = "select gd.NAME, ROLE_NAME, ee.FIRST_NAME, ee.SECOND_NAME, ee.MIDDLE_NAME, rr.FIRST_NAME, rr.second_name, rr.middle_name\n" +
            "from INTERMEDIATE_T3 t3\n" +
            "join GENERAL_DATA_OF_PERFORMANCES gd on gd.PERFORMANCE_ID = t3.ID_PERFORMANCE\n" +
            "join employees ee on ee.id_employee = t3.ID_ACTOR\n" +
            "join ROLES r on r.ID_ROLE = t3.ID_ROLE\n" +
            "join employees rr on rr.id_employee = t3.STUNT_DOUBLE";
    final int rowCount = 8;

    @Override
    public void displayTable(TableView rightTable) throws SQLException {
        rightTable.getColumns().clear();

        ObservableList<TableColumns> strings = FXCollections.observableArrayList();
        TableData data = new TableData(request, rowCount);
        ArrayList<String> tableData = data.getTableData();

        for (int i = 0; i < tableData.size() - rowCount + 1; i += rowCount){

            strings.add(new TableColumns(tableData.get(i), tableData.get(i + 1), tableData.get(i + 2),
                    tableData.get(i + 3), tableData.get(i + 4), tableData.get(i + 5), tableData.get(i + 6),
                    tableData.get(i + 7)));
        }

        TableColumn<TableColumns, String> playName = new TableColumn<>("Name of play");
        playName.setCellValueFactory(new PropertyValueFactory<>("playName"));
        rightTable.getColumns().add(playName);

        TableColumn<TableColumns, String> roleName = new TableColumn<>("Name of role");
        roleName.setCellValueFactory(new PropertyValueFactory<>("roleName"));
        rightTable.getColumns().add(roleName);

        TableColumn<TableColumns, String> actorName = new TableColumn<>("Name of actor");
        actorName.setCellValueFactory(new PropertyValueFactory<>("actorName"));
        rightTable.getColumns().add(actorName);

        TableColumn<TableColumns, String> actorSurname = new TableColumn<>("Surname of actor");
        actorSurname.setCellValueFactory(new PropertyValueFactory<>("actorSurname"));
        rightTable.getColumns().add(actorSurname);

        TableColumn<TableColumns, String> actorMiddleName = new TableColumn<>("Middle name of actor");
        actorMiddleName.setCellValueFactory(new PropertyValueFactory<>("actorMiddleName"));
        rightTable.getColumns().add(actorMiddleName);

        TableColumn<TableColumns, String> stunt_doubleName = new TableColumn<>("Name of stunt double");
        stunt_doubleName.setCellValueFactory(new PropertyValueFactory<>("stunt_doubleName"));
        rightTable.getColumns().add(stunt_doubleName);

        TableColumn<TableColumns, String> stunt_doubleSurname = new TableColumn<>("Surname of stunt double");
        stunt_doubleSurname.setCellValueFactory(new PropertyValueFactory<>("stunt_doubleSurname"));
        rightTable.getColumns().add(stunt_doubleSurname);

        TableColumn<TableColumns, String> stunt_doubleMiddleName = new TableColumn<>("Middle name of stunt double");
        stunt_doubleMiddleName.setCellValueFactory(new PropertyValueFactory<>("stunt_doubleMiddleName"));
        rightTable.getColumns().add(stunt_doubleMiddleName);

        rightTable.setItems(strings);

    }

    public class TableColumns{
        private final SimpleStringProperty playName;
        private final SimpleStringProperty roleName;
        private final SimpleStringProperty actorName;
        private final SimpleStringProperty actorSurname;
        private final SimpleStringProperty actorMiddleName;
        private final SimpleStringProperty stunt_doubleName;
        private final SimpleStringProperty stunt_doubleSurname;
        private final SimpleStringProperty stunt_doubleMiddleName;

        public TableColumns(String playName, String roleName, String actorName, String actorSurname,
                            String actorMiddleName, String stunt_doubleName, String stunt_doubleSurname,
                            String stunt_doubleMiddleName) {
            this.playName = new SimpleStringProperty(playName);
            this.roleName = new SimpleStringProperty(roleName);
            this.actorName = new SimpleStringProperty(actorName);
            this.actorSurname = new SimpleStringProperty(actorSurname);
            this.actorMiddleName = new SimpleStringProperty(actorMiddleName);
            this.stunt_doubleName = new SimpleStringProperty(stunt_doubleName);
            this.stunt_doubleSurname = new SimpleStringProperty(stunt_doubleSurname);
            this.stunt_doubleMiddleName = new SimpleStringProperty(stunt_doubleMiddleName);


        }

        public String getPlayName() {
            return playName.get();
        }

        public String getRoleName() {
            return roleName.get();
        }

        public String getActorName() {
            return actorName.get();
        }

        public String getActorSurname() {
            return actorSurname.get();
        }

        public String getActorMiddleName() {
            return actorMiddleName.get();
        }

        public String getStunt_doubleName() {
            return stunt_doubleName.get();
        }

        public String getStunt_doubleSurname() {
            return stunt_doubleSurname.get();
        }

        public String getStunt_doubleMiddleName() {
            return stunt_doubleMiddleName.get();
        }
    }


    @Override
    public void changeEntry() {

    }

    @Override
    public void deleteEntry() {

    }
}
