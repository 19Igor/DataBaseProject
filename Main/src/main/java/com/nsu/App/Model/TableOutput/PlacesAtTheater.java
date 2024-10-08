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

import java.sql.SQLException;
import java.util.ArrayList;

public class PlacesAtTheater implements Table {

    /*
    * Number of place
    * Part of hall
//    * Coefficient
    *
    * idки оставлять
    * запросы полностью писать в виде строк
    * Следить за количеством столбцу в таблице в результирующей таблице
    * */
    final int rowCount = 3;
    final String request = "select ID_PLACE, SIDE_NAME, PRICE_COEFFICIENT\n" +
                            "from places\n" +
                            "join SIDES_OF_THE_HALL on (ID_SIDE = PART_OF_THE_HALL)";


    @Override
    public void displayTable(TableView rightTable) throws SQLException {

        rightTable.getColumns().clear();

        ObservableList<TableColumns> strings = FXCollections.observableArrayList();
        TableData data = new TableData(request, rowCount);
        ArrayList<String> tableData = data.getTableData();

        for(int i = 0; i < tableData.size() - rowCount + 1; i += rowCount){
            strings.add(new TableColumns(Integer.parseInt(tableData.get(i)), tableData.get(i + 1), Float.parseFloat(tableData.get(i + 2))));
        }

        TableColumn<TableColumns, Integer> place = new TableColumn<>("Number of a place");
        place.setCellValueFactory(new PropertyValueFactory<>("placeID"));
        rightTable.getColumns().add(place);

        TableColumn<TableColumns, Integer> hallPart = new TableColumn<>("Part of a hall");
        hallPart.setCellValueFactory(new PropertyValueFactory<>("hallPart"));
        rightTable.getColumns().add(hallPart);

        TableColumn<TableColumns, Float> coefficient = new TableColumn<>("Coefficient");
        coefficient.setCellValueFactory(new PropertyValueFactory<>("coeff"));
        rightTable.getColumns().add(coefficient);

        rightTable.setItems(strings);
    }


    public class TableColumns {
        private final SimpleIntegerProperty placeID;
        private final SimpleStringProperty hallPart;
        private final SimpleFloatProperty coeff;

        public TableColumns(int id, String side, float coef){
            this.placeID = new SimpleIntegerProperty(id);
            this.hallPart = new SimpleStringProperty(side);
            this.coeff = new SimpleFloatProperty(coef);
        }

        public int getPlaceID() {
            return placeID.get();
        }

        public String getHallPart() {
            return hallPart.get();
        }

        public float getCoeff() {
            return coeff.get();
        }

        public void setPlaceID(int placeID) {
            this.placeID.set(placeID);
        }

        public void setHallPart(String hallPart) {
            this.hallPart.set(hallPart);
        }

        public void setCoeff(float coeff) {
            this.coeff.set(coeff);
        }
    }

    public static ArrayList<String> getRealData() throws SQLException {
        final String request = "select *\n" +
                "from PLACES";
        final int rowCount = 2;

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
