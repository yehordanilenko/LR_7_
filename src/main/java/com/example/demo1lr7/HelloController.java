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
    private TableView<Meta> table;
    @FXML
    private TableColumn<Meta, Long> r030;
    @FXML
    private TableColumn<Meta, String> txt;
    @FXML
    private TableColumn<Meta, Double> rate;
    @FXML
    private TableColumn<Meta, String> cc;
    @FXML
    private TableColumn<Meta, String> exchangedate;

    public void initialize() {
        JSONGetter jsonGetter = new JSONGetter();
        JSONGetter.url = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
        jsonGetter.run();

        String jsonString = jsonGetter.jsonIn;

        Object tempObj = null;
        try
        {
            tempObj = new JSONParser().parse(jsonString);
        }
        catch (org.json.simple.parser.ParseException e)
        {
            e.printStackTrace();
        }

        JSONArray jsonArray = (JSONArray) tempObj;

        ObsList metas = new ObsList();

        for (Object jsonObject : jsonArray)
        {
            JSONObject getMeta = (JSONObject) jsonObject;
            long r030 = (long) getMeta.get("r030");
            String txt = (String) getMeta.get("txt");
            double rate = Double.parseDouble(Double.toString((Double) getMeta.get("rate")));
            String cc = (String) getMeta.get("cc");
            String exchangedate = (String) getMeta.get("exchangedate");

            Meta newMeta = new Meta(r030, txt, rate, cc, exchangedate);
            metas.add(newMeta);
        }

        r030.setCellValueFactory(new PropertyValueFactory<Meta, Long>("r030"));
        txt.setCellValueFactory(new PropertyValueFactory<Meta, String>("txt"));
        rate.setCellValueFactory(new PropertyValueFactory<Meta, Double>("rate"));
        cc.setCellValueFactory(new PropertyValueFactory<Meta, String>("cc"));
        exchangedate.setCellValueFactory(new PropertyValueFactory<Meta, String>("exchangedate"));

        table.setItems(metas.getMetas());
    }
}