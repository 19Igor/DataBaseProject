package com.nsu.App.Model.TableOutput;

import com.nsu.App.Controllers.TableData;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;

// ctrl + enter = getter or setter

public class SidesOfTheHall implements Table {

    final String request = "select *\n" +
                            "from SIDES_OF_THE_HALL";
    final int numberColumn = 3;

    @Override
    public void displayTable(TableView rightTable) throws SQLException {
        rightTable.getColumns().clear();

        // Получения списка строк
        ObservableList<tableColumns> strings = FXCollections.observableArrayList();
        TableData table = new TableData(request, numberColumn);
        ArrayList<String> tableData = table.getTableData();

        // Переносим список строк в ObservableList
        for (int i = 0; i < tableData.size() - numberColumn + 1; i += numberColumn){
            strings.add(new tableColumns(Integer.parseInt(tableData.get(i)), tableData.get(i + 1), Float.parseFloat(tableData.get(i + 2))));
        }

        // Создаю колонки и привязываю их к полям класса tableColumns
          // Убрал так как пользователю не нужен id
        // Но в самой TableView содержутся id

        TableColumn<tableColumns, String> name_of_side = new TableColumn<>("Name of side");
        name_of_side.setCellValueFactory(new PropertyValueFactory<>("sideName"));
        rightTable.getColumns().add(name_of_side);

        TableColumn<tableColumns, Float> coefficient = new TableColumn<>("Coefficient");
        coefficient.setCellValueFactory(new PropertyValueFactory<>("priceCoefficient"));
        rightTable.getColumns().add(coefficient);


        // Запихиваю в таблицу данные
        rightTable.setItems(strings);

    }

    public class tableColumns{

        private final SimpleIntegerProperty idSide;

        private final SimpleStringProperty sideName;
        private final SimpleFloatProperty priceCoefficient;
        public tableColumns(int id, String name, float coeff){
            this.idSide = new SimpleIntegerProperty(id);
            this.sideName = new SimpleStringProperty(name);
            this.priceCoefficient = new SimpleFloatProperty(coeff);
        }


        public int getIdSide(){ return idSide.get(); }

        public void setIdSide(int id){ idSide.set(id); }
        public String getSideName(){ return sideName.get(); }

        public void setSideName(String name){ sideName.set(name); }
        public float getPriceCoefficient(){ return priceCoefficient.get(); }

        public void setPriceCoefficient(float number){ priceCoefficient.set(number); }

    }

    public static ArrayList<String> getRealData() throws SQLException {
        final String request = "select *\n" +
                                "from SIDES_OF_THE_HALL";
        final int realRowCount = 3;

        ArrayList<String> data;
        TableData tableData = new TableData(request, realRowCount);
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

