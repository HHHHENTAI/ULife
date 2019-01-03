package com.example.hhhhentai.JsonGet;

import java.io.Serializable;

public class AnalyzeWeather implements Serializable {
    private String sk;
    private  String today;

    @Override
    public String toString() {
        return "AnalyzeWeather{" +
                "sk='" + sk + '\'' +
                ", today='" + today + '\'' +
                '}';
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getSk() {
        return sk;
    }

    public String getToday() {
        return today;
    }
}
