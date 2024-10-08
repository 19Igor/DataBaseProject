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

public class Authors implements Table {
    final String request = "select NAME, SURNAME, MIDDLE_NAME, DATE_OF_BIRTH, DATE_OF_DEATH, COUNTRY\n" +
                            "from AUTHORS";
    final int rowCount = 6;

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

        TableColumn<TableColumns, String> name1 = new TableColumn<>("First name");
        name1.setCellValueFactory(new PropertyValueFactory<>("fName"));
        rightTable.getColumns().add(name1);

//        TableColumn<TableColumns, String> fName = new TableColumn<>("First name");
//        fName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
//        rightTable.getColumns().add(fName);

        TableColumn<TableColumns, String> name2 = new TableColumn<>("Second name");
        name2.setCellValueFactory(new PropertyValueFactory<>("sName"));
        rightTable.getColumns().add(name2);

        TableColumn<TableColumns, String> name3 = new TableColumn<>("Middle name");
        name3.setCellValueFactory(new PropertyValueFactory<>("mName"));
        rightTable.getColumns().add(name3);

        TableColumn<TableColumns, String> birthDate = new TableColumn<>("Date of birth");
        birthDate.setCellValueFactory(new PropertyValueFactory<>("bDate"));
        rightTable.getColumns().add(birthDate);

        TableColumn<TableColumns, String> deathDate = new TableColumn<>("Date of death");
        deathDate.setCellValueFactory(new PropertyValueFactory<>("dDate"));
        rightTable.getColumns().add(deathDate);

        TableColumn<TableColumn, String> country = new TableColumn<>("County");
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        rightTable.getColumns().add(country);

        rightTable.setItems(strings);

    }

    public class TableColumns{
        private final SimpleStringProperty fName;
        private final SimpleStringProperty sName;
        private final SimpleStringProperty mName;
        private final SimpleStringProperty bDate;
        private final SimpleStringProperty dDate;
        private final SimpleStringProperty country;

        public TableColumns(String name1, String name2, String name3, String bDate, String dDate, String country) {
            this.fName = new SimpleStringProperty(name1);
            this.sName = new SimpleStringProperty(name2);
            this.mName = new SimpleStringProperty(name3);
            this.bDate = new SimpleStringProperty(bDate);
            this.dDate = new SimpleStringProperty(dDate);
            this.country = new SimpleStringProperty(country);
        }

        // После слова get в названии геттера должны начинаться слова с большой буквы.
        public String getFName() {
            return fName.get();
        }

        public String getSName() {
            return sName.get();
        }

        public String getMName() {
            return mName.get();
        }

        public String getDDate() {
            return bDate.get();
        }

        public String getBDate() {
            return dDate.get();
        }

        public String getCountry() {
            return country.get();
        }
    }


    @Override
    public void changeEntry() {

    }

    @Override
    public void deleteEntry() {

    }
}
