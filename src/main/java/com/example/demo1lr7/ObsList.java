package com.example.demo1lr7;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ObsList {
    @JsonProperty("metas")
    ObservableList<Meta> metas;

    public ObsList() {
        metas = FXCollections.observableArrayList();
    }
    public ObservableList<Meta> getMetas() {
        return metas;
    }

    public void add(Meta meta) {
        this.metas.add(meta);
    }

    public ObsList filterByCCY(String ccy) {
        ObsList tempRats = new ObsList();
        for (Meta meta : this.metas) {
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