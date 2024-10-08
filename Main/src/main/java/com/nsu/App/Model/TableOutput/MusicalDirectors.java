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

public class MusicalDirectors implements Table {
    final String request = "select FIRST_NAME, SECOND_NAME, MIDDLE_NAME, START_CONTRACT, END_CONTRACT, EXPERIANCE, " +
                            "SEX, DATE_OF_BIRTH, NUMBER_OF_CHILDREN, SALARY\n" +
                            "from employees e\n" +
                            "join MUSICAL_DIRECTORS md on md.ID_MUS_DIRECTOR = e.ID_EMPLOYEE";

    final int rowCount = 10;

    @Override
    public void displayTable(TableView rightTable) throws SQLException {
        rightTable.getColumns().clear();

        ObservableList<TableColumns> strings = FXCollections.observableArrayList();
        TableData data = new TableData(request, rowCount);
        ArrayList<String> tableData = data.getTableData();

        for (int i = 0; i < tableData.size() - rowCount + 1; i += rowCount){

            int numberOne = 0;
            int numberTwo = 0;
            int numberThree = 0;

            if (tableData.get(i + 5) != "--")
                numberOne = Integer.parseInt(tableData.get(i + 5));
            if (tableData.get(i + 8) != "--")
                numberTwo = Integer.parseInt(tableData.get(i + 8));
            if (tableData.get(i + 9) != "--")
                numberThree = Integer.parseInt(tableData.get(i + 9));

            strings.add(new TableColumns(tableData.get(i), tableData.get(i + 1), tableData.get(i + 2),
                            tableData.get(i + 3), tableData.get(i + 4), numberOne, tableData.get(i + 6),
                            tableData.get(i + 7), numberTwo, numberThree));
        }

        TableColumn<TableColumns, String> fName = new TableColumn<>("First name");
        fName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        rightTable.getColumns().add(fName);

        TableColumn<TableColumns, String> sName = new TableColumn<>("Second name");
        sName.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        rightTable.getColumns().add(sName);

        TableColumn<TableColumns, String> mName = new TableColumn<>("Middle name");
        mName.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        rightTable.getColumns().add(mName);

        TableColumn<TableColumns, String> sContract = new TableColumn<>("Start contract");
        sContract.setCellValueFactory(new PropertyValueFactory<>("startContract"));
        rightTable.getColumns().add(sContract);

        TableColumn<TableColumns, String> eContract = new TableColumn<>("End contract");
        eContract.setCellValueFactory(new PropertyValueFactory<>("endContract"));
        rightTable.getColumns().add(eContract);

        TableColumn<TableColumns, Integer> exp = new TableColumn<>("Experiance");
        exp.setCellValueFactory(new PropertyValueFactory<>("experiance"));
        rightTable.getColumns().add(exp);

        TableColumn<TableColumns, String> sex = new TableColumn<>("Sex");
        sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        rightTable.getColumns().add(sex);

        TableColumn<TableColumns, String> bDate = new TableColumn<>("Date of birth");
        bDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        rightTable.getColumns().add(bDate);

        TableColumn<TableColumns, Integer> childrenCount = new TableColumn<>("Number of children");
        childrenCount.setCellValueFactory(new PropertyValueFactory<>("numberOfChildren"));
        rightTable.getColumns().add(childrenCount);

        TableColumn<TableColumns, Integer> sal = new TableColumn<>("Salary");
        sal.setCellValueFactory(new PropertyValueFactory<>("salary"));
        rightTable.getColumns().add(sal);

        rightTable.setItems(strings);

    }

    public class TableColumns{
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty secondName;
        private final SimpleStringProperty middleName;
        private final SimpleStringProperty startContract;
        private final SimpleStringProperty endContract;
        private final SimpleIntegerProperty experiance;
        private final SimpleStringProperty sex;
        private final SimpleStringProperty birthDate;
        private final SimpleIntegerProperty numberOfChildren;
        private final SimpleIntegerProperty salary;

        public TableColumns(String firstName, String secondName, String middleName, String startContract,
                            String endContract, int experiance, String sex, String birthDate,
                            int numberOfChildren, int salary) {
            this.firstName = new SimpleStringProperty(firstName);
            this.secondName = new SimpleStringProperty(secondName);
            this.middleName = new SimpleStringProperty(middleName);
            this.startContract = new SimpleStringProperty(startContract);
            this.endContract = new SimpleStringProperty(endContract);
            this.experiance = new SimpleIntegerProperty(experiance);
            this.sex = new SimpleStringProperty(sex);
            this.birthDate = new SimpleStringProperty(birthDate);
            this.numberOfChildren = new SimpleIntegerProperty(numberOfChildren);
            this.salary = new SimpleIntegerProperty(salary);
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

        public String getStartContract() {
            return startContract.get();
        }

        public String getEndContract() {
            return endContract.get();
        }

        public int getExperiance() {
            return experiance.get();
        }

        public String getSex() {
            return sex.get();
        }

        public String getBirthDate() {
            return birthDate.get();
        }

        public int getNumberOfChildren() {
            return numberOfChildren.get();
        }

        public int getSalary() {
            return salary.get();
        }
    }

    @Override
    public void changeEntry() {

    }

    @Override
    public void deleteEntry() {

    }
}
