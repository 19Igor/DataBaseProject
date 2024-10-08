package com.nsu.App.Controllers;

import com.nsu.App.Application;
import com.nsu.App.Model.TableOutput.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class WindowCreater {
    static ArrayList<TextField> fields = new ArrayList<>();
    static int rowCount;
    static TableView rightTable;
    static String tableName;
    static Pane rootPane;

    final private String URL        = "jdbc:oracle:thin:@" + "84.237.50.81:1521" + ":";
    final private String USER_NAME  = "20211_EPOV";
    final private String PASS       = "20211_EPOV";

    ArrayList<String> list = new ArrayList<>();

    @FXML
    Button buttonAdd;
    @FXML
    AnchorPane inputAnchorPane;

    public WindowCreater() {

        /*
        * Когда fxmlloader загружает fxml файл и создаёт экземпляр контролера, он пытается вызвать конструктор
        * без параметров
        * FXMLLoader в JavaFX используется для загрузки FXML-файлов и создания связанных с ними контроллеров.
        * Когда вызывается метод `fxmlLoader.load()`, происходит следующий процесс:
        * 1. FXMLLoader анализирует содержимое FXML-файла и находит корневой элемент (root element) и его дочерние элементы,
        * такие как кнопки, текстовые поля и другие элементы пользовательского интерфейса.
        * 2. При анализе FXML-файла FXMLLoader также ищет атрибут `fx:controller`, который указывает класс контроллера,
        * связанного с FXML-файлом. Этот атрибут может выглядеть примерно так: `fx:controller="com.example.MyController"`.
        * 3. Когда FXMLLoader находит атрибут `fx:controller`, он пытается создать экземпляр класса контроллера.
        * Для этого FXMLLoader использует рефлексию и ищет конструктор без аргументов (default constructor)
        * в классе контроллера.
        * 4. Если конструктор без аргументов найден, FXMLLoader создает экземпляр контроллера с помощью этого конструктора.
        * 5. После создания экземпляра контроллера, FXMLLoader устанавливает его как контроллер для загруженного FXML-файла.
        * Это означает, что контроллер будет иметь доступ к элементам пользовательского интерфейса, описанным в
        * FXML-файле, и может управлять ими.
        * 6. Наконец, метод `fxmlLoader.load()` возвращает корневой элемент (root element) FXML-файла,
        * обычно типизированный как `Parent`, который представляет верхний уровень иерархии элементов
        * пользовательского интерфейса.
        * В случае, когда не удается найти конструктор без аргументов или возникают другие проблемы при создании
        * экземпляра контроллера, возникают ошибки, подобные той, которую вы видели. В этом случае стек вызовов ошибки
        * может указывать на место, где произошла проблема, например, на строку в FXML-файле, где указан контроллер,
        * или на конструктор контроллера.
        * Поэтому важно убедиться, что класс контроллера имеет конструктор без аргументов и правильно настроен, чтобы
        * FXMLLoader мог успешно создать экземпляр контроллера при загрузке FXML-файла.
        *
        *
        * */
    }

    public WindowCreater(String tableName, int number, TableView tableView) {
        this.tableName = tableName;
        this.rowCount = number;
        this.rightTable = tableView;

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Makets/InputParamTable.fxml"));

        try {
            Scene loadedScene = new Scene(fxmlLoader.load());

            HBox hBox = new HBox(0);
            HBox.setHgrow(hBox, Priority.ALWAYS);
            AnchorPane.setTopAnchor(hBox, 0.0);
            AnchorPane.setLeftAnchor(hBox, 0.0);
            AnchorPane.setRightAnchor(hBox, 0.0);

            rootPane = (Pane) loadedScene.getRoot();
            rootPane.getChildren().add(hBox);
            for (int i = 0; i < rowCount; i++) {
                fields.add(new TextField());
                hBox.getChildren().add(fields.get(i));
            }

            Stage stage = new Stage();
            stage.setScene(loadedScene);
            stage.maximizedProperty();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*
    * При каждом нажатии на кнопку будет создаваться новый объект класса WindowCreater и все
    * поля метода будут проинициализированы дефолтными значениями.
    * */


    // это кнопка, которая находится в окне для ввода новой записи
    @FXML
    private void getAndSendColumnsOfNewEntry() throws SQLException {
        rootPane.getScene().getWindow().hide();
        // получили данные НОВОЙ записи в виде списка стрингов
        for (int i = 0; i < rowCount; i++){
            list.add(fields.get(i).getText());
        }

        switch (tableName) {
            case "PLACES" -> {
                PlacesAtTheater table = new PlacesAtTheater();
                ArrayList<String> hallSides = SidesOfTheHall.getRealData();
                ArrayList<String> places = PlacesAtTheater.getRealData();
                int hallPrice = getEntryIndex(hallSides, list.get(1), 1, 3);
                list.set(1, Integer.toString(hallPrice));
                int maxID = findMaxID(places, 2);
                createRequest(list,-1);
                table.displayTable(rightTable);
            }
            case "SOLD_TICKETS"                     -> {
                // требуется тестирование
                SoldTickets table = new SoldTickets();
                ArrayList<String> times = DatesAndPlays.getRealData();
                ArrayList<String> soldTickets = SoldTickets.getRealData();
                int timeKey = getEntryIndex(times, list.get(1), 0, 3);
                list.set(1, Integer.toString(timeKey));
                int maxID = findMaxID(soldTickets, 5);
                createRequest(list, maxID + 1);
                table.displayTable(rightTable);
            }
//            case "Dates and plays"                  -> {
//                DatesAndPlays table = new DatesAndPlays();
//                table.displayTable(rightTable);
//            }
//            case "Positions"                        -> {
//                Positions table = new Positions();
//                table.displayTable(rightTable);
//            }
//            case "Employees"                        -> {
//                Employees table = new Employees();
//                table.displayTable(rightTable);
//            }
//            case "Musicians"                        -> {
//                Musicians table = new Musicians();
//                table.displayTable(rightTable);
//            }
//            case "Film directors"                   -> {
//                FilmDirectors table = new FilmDirectors();
//                table.displayTable(rightTable);
//            }
//            case "Director-directors"               -> {
//                DirectorDirectors table = new DirectorDirectors();
//                table.displayTable(rightTable);
//            }
//            case "Musical Directors"                -> {
//                MusicalDirectors table = new MusicalDirectors();
//                table.displayTable(rightTable);
//            }
//            case "Artist directors"                 -> {
//                ArtistDirectors table = new ArtistDirectors();
//                table.displayTable(rightTable);
//            }
//            case "Authors"                          -> {
//                Authors table = new Authors();
//                table.displayTable(rightTable);
//            }
//            case "Theaters"                         -> {
//                Theaters table = new Theaters();
//                table.displayTable(rightTable);
//            }
//            case "General data of plays"            -> {
//                GeneralData table = new GeneralData();
//                table.displayTable(rightTable);
//            }
//            case "Theaters and plays"               -> {
//                TheatersAndPlays table = new TheatersAndPlays();
//                table.displayTable(rightTable);
//            }
//            case "Specified data of plays"          -> {
//                SpecifiedData table = new SpecifiedData();
//                table.displayTable(rightTable);
//            }
//            case "Tours"                            -> {
//                Tours table = new Tours();
//                table.displayTable(rightTable);
//            }
//            case "Tours were"                       -> {
//                ToursWere table = new ToursWere();
//                table.displayTable(rightTable);
//            }
//            case "Competitions"                     -> {
//                Competitions table = new Competitions();
//                table.displayTable(rightTable);
//            }
//            case "Ranks"                            -> {
//                Ranks table = new Ranks();
//                table.displayTable(rightTable);
//            }
//            case "Actors"                           -> {
//                Actors table = new Actors();
//                table.displayTable(rightTable);
//            }
//            case "Actors and competitions"          -> {
//                ActorsAndCompetitions table = new ActorsAndCompetitions();
//                table.displayTable(rightTable);
//            }
            case "ROLES" -> {
                Roles table = new Roles();
                // Полностью загружаем таблицы
                ArrayList<String> voices = Voices.getRealData();
                ArrayList<String> genData = GeneralData.getRealData();
                ArrayList<String> roles = Roles.getRealData();

                // находим id подходящей записи
                int voiceKey = getEntryIndex(voices, list.get(3), 1, 2);
                int genDataKey = getEntryIndex(genData, list.get(4), 1, 6);
                // на соответствующую таблицу заносим соответствующий id соответствующей записи
                list.set(3, Integer.toString(voiceKey));
                list.set(4, Integer.toString(genDataKey));

                // Находим максимальный id
                int id = findMaxID(roles, 6);
                createRequest(list, id + 1);

                table.displayTable(rightTable);
                int a = 0;
            }
//            case "Plays and actors and roles and stunt double"   -> {
//                PARSD table = new PARSD();
//                table.displayTable(rightTable);
//            }
            default -> throw new IllegalStateException("Unexpected value: " + tableName);
        }

    }


    private void createRequest(ArrayList<String> entry, int id){
        // INSERT INTO SIDES_OF_THE_HALL VALUES(1, 'Бельэтаж')
        String req1 = "INSERT INTO " + tableName + " VALUES(";
        if (id != -1){
            req1 += id + ", ";
        }
        for (int i = 0; i < entry.size(); i++){
            if (isNumeric(entry.get(i))){
                req1 += entry.get(i);
            }
            else{
                req1 += "'" + entry.get(i) + "'";
            }
            if (i == entry.size() - 1){
                req1 += ")";
            }
            else{
                req1 += ", ";
            }
        }

        sendRequest(req1);
    }

    public void sendRequest(String request){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn;
        Locale.setDefault(Locale.ENGLISH);

        try {
            conn = DriverManager.getConnection(URL, USER_NAME, PASS);

            if (conn == null) {
                System.out.println("Нет соединения с БД!");
                System.exit(0);
            }

            Statement statement = conn.createStatement();
            int code = statement.executeUpdate(request);
            System.out.println(code);
//            ResultSet resultSet = statement.executeQuery(request);

            statement.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private int getEntryIndex(ArrayList<String> realData, String key, int startPosition, int realRowNumber){
        int id = -1;
        for (int i = startPosition; i < realData.size(); i += realRowNumber){
            if (key.equals(realData.get(i))){
                id = Integer.parseInt(realData.get(i - 1));
                return id;
            }
        }
        return id;
    }

    private int findMaxID(ArrayList<String> arrayList, int rowNumber){
        int id = Integer.parseInt(arrayList.get(0));

        for (int i = 0; i < arrayList.size(); i += rowNumber)
            if (id < Integer.parseInt(arrayList.get(i)))
                id = Integer.parseInt(arrayList.get(i));

        return id;
    }

}

