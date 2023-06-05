package com.nsu.App.Controllers;

import com.nsu.App.Model.TableOutput.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import java.sql.SQLException;

public class TableController {

    ObservableList<String> availableTables = FXCollections.observableArrayList(
            "Sides of the hall",
            "Places at a theater",
            "Sold tickets",
            "Dates and plays",
            "Positions",
            "Employees",
            "Musicians",
            "Film directors",
            "Director-directors",
            "Musical Directors",
            "Artist directors",
            "Authors",
            "Theaters",
            "General data of plays",
            "Theaters and plays",
            "Specified data of plays",
            "Tours",
            "Tours were",
            "Competitions",
            "Ranks",
            "Actors",
            "Actors and competitions",
            "Roles",
            "Plays and actors and roles and stunt double"
    );

    @FXML
    ChoiceBox<String> itemList;

    @FXML
    AnchorPane leftAnchorPane;

    @FXML
    TableView rightTable;

    @FXML
    Button addButton;      // эта та кнопка, которая находится, где и остальные кнопки

    Para couple;
    WindowCreater windowCreater;


    @FXML
    void initialize() {
        itemList.setItems(availableTables);
        itemList.setOnAction(actionEvent -> {
            try {
                couple = itemHandler(itemList.getValue());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });


        addButton.setOnAction(actionEvent -> {
            if(couple != null)
                windowCreater = new WindowCreater(couple.tableName, couple.amountOfRows, rightTable);
            else
                System.out.println("Это null");
//            windowCreater = new WindowCreater("ROLES", 5, rightTable);         // тестовая строка
        });



    }

    public Para itemHandler(String tableName) throws SQLException {

        // При выборе таблицы будут происходить действия ниже
        // Определять поведение кнопок будем уже здесь: добавление, удаление, изменение
        AnchorPane.setLeftAnchor(rightTable, 0.0);

        switch (tableName) {
            case "Sides of the hall"              -> {
                PlacesAtTheater table = new PlacesAtTheater();
                table.displayTable(rightTable);
                return new Para(2, "SIDES_OF_THE_HALL");
            }
            case "Places at a theater"              -> {
                PlacesAtTheater table = new PlacesAtTheater();
                table.displayTable(rightTable);
                return new Para(2, "PLACES");
            }
            case "Sold tickets"                     -> {
                SoldTickets table = new SoldTickets();
                table.displayTable(rightTable);
                return new Para(3, "SOLD_TICKETS");
            }
            case "Dates and plays"                  -> {
                DatesAndPlays table = new DatesAndPlays();
                table.displayTable(rightTable);
            }
            case "Positions"                        -> {
                Positions table = new Positions();
                table.displayTable(rightTable);
            }
            case "Employees"                        -> {
                Employees table = new Employees();
                table.displayTable(rightTable);
            }
            case "Musicians"                        -> {
                Musicians table = new Musicians();
                table.displayTable(rightTable);
            }
            case "Film directors"                   -> {
                FilmDirectors table = new FilmDirectors();
                table.displayTable(rightTable);
            }
            case "Director-directors"               -> {
                DirectorDirectors table = new DirectorDirectors();
                table.displayTable(rightTable);
            }
            case "Musical Directors"                -> {
                MusicalDirectors table = new MusicalDirectors();
                table.displayTable(rightTable);
            }
            case "Artist directors"                 -> {
                ArtistDirectors table = new ArtistDirectors();
                table.displayTable(rightTable);
            }
            case "Authors"                          -> {
                Authors table = new Authors();
                table.displayTable(rightTable);
            }
            case "Theaters"                         -> {
                Theaters table = new Theaters();
                table.displayTable(rightTable);
            }
            case "General data of plays"            -> {
                GeneralData table = new GeneralData();
                table.displayTable(rightTable);
            }
            case "Theaters and plays"               -> {
                TheatersAndPlays table = new TheatersAndPlays();
                table.displayTable(rightTable);
            }
            case "Specified data of plays"          -> {
                SpecifiedData table = new SpecifiedData();
                table.displayTable(rightTable);
            }
            case "Tours"                            -> {
                Tours table = new Tours();
                table.displayTable(rightTable);
            }
            case "Tours were"                       -> {
                ToursWere table = new ToursWere();
                table.displayTable(rightTable);
            }
            case "Competitions"                     -> {
                Competitions table = new Competitions();
                table.displayTable(rightTable);
            }
            case "Ranks"                            -> {
                Ranks table = new Ranks();
                table.displayTable(rightTable);
            }
            case "Actors"                           -> {
                Actors table = new Actors();
                table.displayTable(rightTable);
            }
            case "Actors and competitions"          -> {
                ActorsAndCompetitions table = new ActorsAndCompetitions();
                table.displayTable(rightTable);
            }
            case "Roles"                            -> {
                Roles table = new Roles();
                table.displayTable(rightTable);
                return new Para(5, "ROLES");
            }
            case "Plays and actors and roles and stunt double"   -> {
                PARSD table = new PARSD();
                table.displayTable(rightTable);
            }
        }
        return null;
    }
}


class Para{
    int amountOfRows = 0;
    String tableName;

    public Para(int notRealAmountOfRows, String str) {
        this.amountOfRows = notRealAmountOfRows;
        this.tableName = str;
    }
}