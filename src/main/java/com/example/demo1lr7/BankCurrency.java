package com.example.demo1lr7;

import java.io.Serializable;
import java.util.Comparator;

public class BankCurrency implements Serializable {

    private final static long serialVersionUID = 4976987307641598477L;

    private long r030;
    private String txt;
    private double rate;
    private String cc;
    private String exchangedate;

    public BankCurrency(long r030, String txt, double rate, String cc, String exchangedate) {
        this.setR030(r030);
        this.setTxt(txt);
        this.setRate(rate);
        this.setCc(cc);
        this.setExchangedate(exchangedate);
    }

    public BankCurrency() {

    }

    public long getR030() {
        return r030;
    }

    public void setR030(long r030) {
        this.r030 = r030;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getExchangedate() {
        return exchangedate;
    }

    public void setExchangedate(String exchangedate) {
        this.exchangedate = exchangedate;
    }


}

