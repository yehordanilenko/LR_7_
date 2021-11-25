package com.example.demo1lr7;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class HelloController {
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
    private TableColumn<BankCurrency, String> exchangedate;

    public void loadCurrency() {
        JSONGetter jsonGetter = new JSONGetter();
        JSONGetter.url = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
        jsonGetter.run();

        String jsonString = jsonGetter.jsonIn;

        if(jsonString!="Couldn't find API") {

            Object tempObj = null;
            try {
                tempObj = new JSONParser().parse(jsonString);
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }

            JSONArray jsonArray = (JSONArray) tempObj;

            CurrencyObservableList metas = new CurrencyObservableList();

            for (Object jsonObject : jsonArray) {
                JSONObject getMeta = (JSONObject) jsonObject;
                long r030 = (long) getMeta.get("r030");
                String txt = (String) getMeta.get("txt");
                double rate = Double.parseDouble(Double.toString((Double) getMeta.get("rate")));
                String cc = (String) getMeta.get("cc");
                String exchangedate = (String) getMeta.get("exchangedate");

                BankCurrency newMeta = new BankCurrency(r030, txt, rate, cc, exchangedate);
                metas.add(newMeta);
            }

            r030.setCellValueFactory(new PropertyValueFactory<BankCurrency, Long>("r030"));
            txt.setCellValueFactory(new PropertyValueFactory<BankCurrency, String>("txt"));
            rate.setCellValueFactory(new PropertyValueFactory<BankCurrency, Double>("rate"));
            cc.setCellValueFactory(new PropertyValueFactory<BankCurrency, String>("cc"));
            exchangedate.setCellValueFactory(new PropertyValueFactory<BankCurrency, String>("exchangedate"));

            table.setItems(metas.getMetas());
            correctLabel.setText("Данные успешно загружены!");
        }else {
            correctLabel.setText("Данные не доступны, поэтому не были загружены!");
        }
    }
}