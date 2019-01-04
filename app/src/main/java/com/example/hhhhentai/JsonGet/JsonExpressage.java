package com.example.hhhhentai.JsonGet;

import java.io.Serializable;

public class JsonExpressage implements Serializable {
    private String com;
    private String no;

    @Override
    public String toString() {
        return  com ;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getCom() {
        return com;
    }

    public String getNo() {
        return no;
    }
}
