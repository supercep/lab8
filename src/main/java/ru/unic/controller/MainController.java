package ru.unic.controller;

import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import javax.swing.text.html.ImageView;

public class MainController implements Initializable{


    @FXML // fx:id="loginButton"
    private Button loginButton; // Value injected by FXMLLoader

    @FXML // fx:id="nameField"
    private TextField nameField; // Value injected by FXMLLoader

    @FXML // fx:id="sonameField"
    private TextField sonameField; // Value injected by FXMLLoader

    @FXML // fx:id="patroField"
    private TextField patroField; // Value injected by FXMLLoader

    @FXML // fx:id="groupField"
    private TextField groupField; // Value injected by FXMLLoader

    @FXML // fx:id="helloTab"
    private Label helloTab; // Value injected by FXMLLoader

    @FXML // fx:id="tabPane"
    private TabPane tabPane; // Value injected by FXMLLoader

    @FXML // fx:id="tabPane"
    private Tab tabPaneReg; // Value injected by FXMLLoader

    @FXML // fx:id="tabPane"
    private Tab tabPaneAbout; // Value injected by FXMLLoader

    @FXML // fx:id="tabPaneCharts"
    private Tab tabPaneCharts; // Value injected by FXMLLoader

    @FXML // fx:id="passwordField"
    private  PasswordField passwordField; // Value injected by FXMLLoader

    @FXML // fx:id="textareaField"
    private  TextArea textareaField; // Value injected by FXMLLoader

    @FXML // fx:id="dateCalendar"
    private  TextField dateCalendar; // Value injected by FXMLLoader

    @FXML // fx:id="xValue"
    private  TextField xValue; // Value injected by FXMLLoader

    @FXML // fx:id="yValue"
    private  TextField yValue; // Value injected by FXMLLoader

    @FXML // fx:id="eraseButton"
    private  Button eraseButton; // Value injected by FXMLLoader

    @FXML // fx:id="barChart"
    private BarChart barChart; // Value injected by FXMLLoader

    @FXML // fx:id="x"
    private CategoryAxis x; // Value injected by FXMLLoader

    @FXML // fx:id="y"
    private NumberAxis y; // Value injected by FXMLLoader

    @FXML // fx:id="createBarButton"
    private Button createBarButton; // Value injected by FXMLLoader

    public void initialize(URL location, ResourceBundle resources) {

        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'hello.fxml'.";
        assert createBarButton != null : "fx:id=\"createBarButton\" was not injected: check your FXML file 'hello.fxml'.";
        assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'hello.fxml'.";
        assert sonameField != null : "fx:id=\"sonameField\" was not injected: check your FXML file 'hello.fxml'.";
        assert patroField != null : "fx:id=\"patroField\" was not injected: check your FXML file 'hello.fxml'.";
        assert groupField != null : "fx:id=\"groupField\" was not injected: check your FXML file 'hello.fxml'.";
        assert passwordField != null : "fx:id=\"passTextField\" was not injected: check your FXML file 'hello.fxml'.";
        assert helloTab != null : "fx:id=\"helloTab\" was not injected: check your FXML file 'hello.fxml'.";
        assert tabPane != null : "fx:id=\"tabPane\" was not injected: check your FXML file 'hello.fxml'.";
        assert tabPaneReg != null : "fx:id=\"tabPaneReg\" was not injected: check your FXML file 'hello.fxml'.";
        assert tabPaneCharts != null : "fx:id=\"tabPaneCharts\" was not injected: check your FXML file 'hello.fxml'.";
        assert tabPaneAbout != null : "fx:id=\"tabPaneAbout\" was not injected: check your FXML file 'hello.fxml'.";
        assert textareaField != null : "fx:id=\"textareaField\" was not injected: check your FXML file 'hello.fxml'.";
        assert dateCalendar != null : "fx:id=\"dateCalendar\" was not injected: check your FXML file 'hello.fxml'.";
        assert eraseButton != null : "fx:id=\"eraseButton\" was not injected: check your FXML file 'hello.fxml'.";
        assert barChart != null : "fx:id=\"barChart\" was not injected: check your FXML file 'hello.fxml'.";
        assert y != null : "fx:id=\"y\" was not injected: check your FXML file 'hello.fxml'.";
        assert x != null : "fx:id=\"y\" was not injected: check your FXML file 'hello.fxml'.";
        assert yValue != null : "fx:id=\"yValue\" was not injected: check your FXML file 'hello.fxml'.";
        assert xValue != null : "fx:id=\"xValue\" was not injected: check your FXML file 'hello.fxml'.";
    }



    public void loginAction(ActionEvent actionEvent) {

        if(fieldsChecker("login")){ //Проверяем, все ли поля заполнены и если да, то приветствуем
            tabPaneAbout.setDisable(false);
            String fullInfo = sonameField.getText() + " " + nameField.getText() + " " + patroField.getText() + " " + groupField.getText() + " " + halfDay("getTime") + "\n";
            putToDB(fullInfo);
        }
    }

    private void putToDB(String fullInfo) {
        String currentDate = halfDay("date");
        File filePath = new File("historyLogs");
        filePath.mkdir();
        File file = new File(filePath + "/" + currentDate + ".txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(FileWriter writer = new FileWriter(filePath + "/" + currentDate + ".txt", true))
        {
            // запись всей строки
            writer.write(fullInfo);

            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }


    public void getPassword(ActionEvent actionEvent) {  //passAction on EnterPress
        String currentDate;
        if(dateCalendar.getText().isEmpty() || dateCalendar.getText() == ""){
            currentDate = halfDay("date");
        }else {
            currentDate = dateCalendar.getText();
        }

        if(passwordField.getText().equals("Удалов")){
            textareaField.setText("");
            int count = 0;
            File filePath = new File("historyLogs");
            filePath.mkdir();

            try(FileReader reader = new FileReader(filePath + "/" + currentDate + ".txt"))
            {
                // читаем посимвольно
                int c;
                String stage = new String();
                while((c=reader.read())!=-1){
                    stage += (char)c;
                }

                textareaField.setText(stage);

            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
            passwordField.setText("");

        }
    }

    public boolean fieldsChecker(String action){
        if(nameField.getText() == null || nameField.getText().trim().isEmpty()) {
            helloTab.setVisible(true);
            helloTab.setText("Вы не ввели имя.");
            return false;
        }else if(sonameField.getText() == null || sonameField.getText().trim().isEmpty()){
            helloTab.setVisible(true);
            helloTab.setText("Вы не ввели Фамилию.");
            return false;
        }else if(patroField.getText() == null || patroField.getText().trim().isEmpty()){
            helloTab.setVisible(true);
            helloTab.setText("+, если нет отчества.");
            return false;
        }else if(groupField.getText() == null || groupField.getText().trim().isEmpty()) {
            helloTab.setVisible(true);
            helloTab.setText("Введите группу.");
            return false;
        }else {
            helloTab.setVisible(true);
            if (action == "login") {
                helloTab.setText(halfDay("time") + nameField.getText() + "!");
                return true;
            }
        }
        return false;
    }

    public String halfDay(String half){ // По текущему времени вычисляем часть дня
        String halfOfDay = new String();
        String minHalf[];
        int minutes, hours;
        Date now= new Date();

        if(half == "time") {

            DateFormat df = new SimpleDateFormat("HH:mm");

            // Model Data
            String dateTimeString = df.format(now);

            minHalf = dateTimeString.split(":");

            hours = Integer.parseInt(minHalf[0]);
            minutes = Integer.parseInt(minHalf[1]);
            if ((hours >= 6 && minutes >= 0) && (hours <= 11 && minutes <= 59)) {
                halfOfDay = "Доброе утро, ";
            } else if ((hours >= 12 && minutes >= 0) && (hours <= 18 && minutes <= 59)) {
                halfOfDay = "Добрый день, ";
            } else if ((hours >= 19 && minutes >= 0) && (hours <= 21 && minutes <= 59)) {
                halfOfDay = "Добрый вечер, ";
            } else if (((hours >= 22 && minutes >= 0) && (hours <= 23 && minutes <= 59) || (hours >= 0 && minutes >= 0) && (hours <= 5 && minutes <= 59))) {
                halfOfDay = "Доброй ночи, ";
            }
        }else if(half == "date") {
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

            // Model Data
            halfOfDay = df.format(now);
        }else if(half == "getTime"){
            DateFormat df = new SimpleDateFormat("HH:mm");

            // Model Data
            halfOfDay = df.format(now);
        }
        return halfOfDay;
    }

    public void selectStat(Event event) {
        setDatesOnTable();

    }

    public void eraseStat(ActionEvent actionEvent) {
        setDatesOnTable();
        dateCalendar.setText("");
    }

    public void setDatesOnTable(){
        File filePath = new File("historyLogs");
        int count = 0;
        String[] files = filePath.list(new FilenameFilter() {

            @Override public boolean accept(File folder, String name) {
                return name.endsWith(".txt");
            }

        });
        for(int b=0;b<files.length;b++)
        {
            count++;
        }
        String fileList = new String();
        for (int i = 0; i<count; i++){
            fileList += files[i];
        }

        count = 0;

        String[] dateList = fileList.split(".txt");

        for(int b=0;b<dateList.length;b++)
        {
            count++;
        }
        String dateListArr = new String();
        dateListArr += "Сейчас, если у Вас есть доступ, Вы можете Выбрать эти даты: \n";
        for (int i = 0; i<count; i++){
            dateListArr += dateList[i] + "\n";
        }

        textareaField.setText(dateListArr);

    }

    public void createBar(ActionEvent actionEvent) {
        XYChart.Series series1 = new XYChart.Series<>();
        String xVal = xValue.getText();
        String yVal = yValue.getText();
        int yVa = Integer.parseInt(yVal);
        series1.getData().add(new XYChart.Data(xVal, yVa));


        barChart.getData().addAll(series1);
    }
}
