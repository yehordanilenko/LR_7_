package com.example.demo1lr7;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class HelloController {
    public Button button;
    @FXML
    private Label correctLabel;
    @FXML
    private TableView<BankCurrency> table;
    @FXML
    private TableColumn<BankCurrency, Long> r030;
    @FXML
    private TableColumn<BankCurrency, String> txt;
    @FXML
    private TableColumn<BankCurrency, Double> rate;
    @FXML
    private TableColumn<BankCurrency, String> cc;
    @FXML
    private TableColumn<BankCurrency, String> exchangeDate;
    public static final int MS_TIMEOUT = 50;
    public static final int TIMEOUT = 30000;

    public void loadCurrency() throws InterruptedException {
        JSONGetter jsonGetter = new JSONGetter();
        JSONGetter.url = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
        jsonGetter.start();
        int msForWaiting = 0;
        do {
            msForWaiting += MS_TIMEOUT;
            Thread.sleep(MS_TIMEOUT); //milliseconds

        } while (msForWaiting <= TIMEOUT && jsonGetter.jsonIn.equals(""));
        if (msForWaiting >= TIMEOUT) {

            return;
        }
        String jsonString = jsonGetter.jsonIn;

        if(!jsonString.equalsIgnoreCase("Couldn't find API")) {

            Object tempObj = null;
            try {
                tempObj = new JSONParser().parse(jsonString);
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }

            JSONArray jsonArray = (JSONArray) tempObj;

            CurrencyObservableList metas = new CurrencyObservableList();

            assert jsonArray != null;
            for (Object jsonObject : jsonArray) {
                JSONObject getMeta = (JSONObject) jsonObject;
                long r030 = (long) getMeta.get("r030");
                String txt = (String) getMeta.get("txt");
                double rate = Double.parseDouble(Double.toString((Double) getMeta.get("rate")));
                String cc = (String) getMeta.get("cc");
                String exchangeDate = (String) getMeta.get("exchangedate");

                BankCurrency newMeta = new BankCurrency(r030, txt, rate, cc, exchangeDate);
                metas.add(newMeta);
            }

            r030.setCellValueFactory(new PropertyValueFactory<>("r030"));
            txt.setCellValueFactory(new PropertyValueFactory<>("txt"));
            rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
            cc.setCellValueFactory(new PropertyValueFactory<>("cc"));
            exchangeDate.setCellValueFactory(new PropertyValueFactory<>("exchangeDate"));

            table.setItems(metas.getMetas());
            correctLabel.setText("Данные успешно загружены!");
        }else {
            correctLabel.setText("Данные не доступны, поэтому не были загружены!");
        }
    }
}
