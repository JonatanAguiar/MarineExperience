/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication31.model;

import java.sql.Date;

/**
 *
 * @author jonat
 */
public class Ais {
    private int id;
    private String msg;
    private Date data;

    public Ais(){}
    
    public Ais(int id, String msg, Date data) {
        this.id = id;
        this.msg = msg;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public Date getData() {
        return data;
    }

    @Override
    public String toString() {
        return id + msg + data + "";
    }
}
