package com.example.demo1lr7;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CurrencyObservableList {
    @JsonProperty("metas")
    ObservableList<BankCurrency> metas;

    public CurrencyObservableList() {
        metas = FXCollections.observableArrayList();
    }
    public ObservableList<BankCurrency> getMetas() {
        return metas;
    }

    public void add(BankCurrency meta) {
        this.metas.add(meta);
    }

    public CurrencyObservableList filterByCCY(String ccy) {
        CurrencyObservableList tempRats = new CurrencyObservableList();
        for (BankCurrency meta : this.metas) {
            if (meta.getTxt().toLowerCase().contains(ccy.toLowerCase()))
                tempRats.add(meta);
        }
        return tempRats;
    }



    @Override
    public String toString() {
        return "Metas{" +
                "metas=" + metas +
                '}';
    }
}