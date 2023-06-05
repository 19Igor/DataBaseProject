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

public class SoldTickets implements Table {

    final int rowCount = 4;
    final String request = "select PLACE, DATE_OF_SALE, t4.PERFORMANCE_DATE, TICKET_PRICE\n" +
                            "from SOLD_TICKETS st\n" +
                            "join INTERMEDIATE_T4 t4 on (st.PERFORMANCE_DATE = t4.ID_DATE)\n" +
                            "order by PLACE";

    @Override
    public void displayTable(TableView rightTable) throws SQLException {

        rightTable.getColumns().clear();

        ObservableList<TableColumns> strings = FXCollections.observableArrayList();
        TableData data = new TableData(request, rowCount);
        ArrayList<String> tableData = data.getTableData();

        for (int i = 0; i < tableData.size() - rowCount + 1; i += rowCount){
            strings.add(new TableColumns(Integer.parseInt(tableData.get(i)),
                                        tableData.get(i + 1), tableData.get(i + 2),
                                        Integer.parseInt(tableData.get(i + 3))));
        }

        TableColumn<TableColumns, Integer> ticketPlace = new TableColumn<>("Place of a ticket");
        ticketPlace.setCellValueFactory(new PropertyValueFactory<>("place"));
        rightTable.getColumns().add(ticketPlace);

        TableColumn<TableColumns, String> saleDate = new TableColumn<>("Date of sale");
        saleDate.setCellValueFactory(new PropertyValueFactory<>("dateOfSale"));
        rightTable.getColumns().add(saleDate);

        TableColumn<TableColumns, String> playDate = new TableColumn<>("Date of play");
        playDate.setCellValueFactory(new PropertyValueFactory<>("dateOfPlay"));
        rightTable.getColumns().add(playDate);

        TableColumn<TableColumns, Integer> ticketPrice = new TableColumn<>("Price of a ticket");
        ticketPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        rightTable.getColumns().add(ticketPrice);

        rightTable.setItems(strings);
    }


    public class TableColumns{
        private final SimpleIntegerProperty place;
        private final SimpleStringProperty dateOfSale;
        private final SimpleStringProperty dateOfPlay;
        private final SimpleIntegerProperty price;

        public TableColumns(int place, String saleDate, String playDate, int price){
            this.place      = new SimpleIntegerProperty(place);
            this.dateOfSale = new SimpleStringProperty(saleDate);
            this.dateOfPlay = new SimpleStringProperty(playDate);
            this.price      = new SimpleIntegerProperty(price);

        }

        public int getPlace() {
            return place.get();
        }

        public String getDateOfSale() {
            return dateOfSale.get();
        }

        public String getDateOfPlay() {
            return dateOfPlay.get();
        }

        public int getPrice() {
            return price.get();
        }

        public void setPlace(int place) {
            this.place.set(place);
        }

        public void setDateOfSale(String dateOfSale) {
            this.dateOfSale.set(dateOfSale);
        }

        public void setDateOfPlay(String dateOfPlay) {
            this.dateOfPlay.set(dateOfPlay);
        }

        public void setPrice(int price) {
            this.price.set(price);
        }
    }

    public static ArrayList<String> getRealData() throws SQLException {
        final String request = "select *\n" +
                "from SOLD_TICKETS";
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
