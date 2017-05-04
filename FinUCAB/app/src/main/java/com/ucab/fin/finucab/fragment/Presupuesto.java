package com.ucab.fin.finucab.fragment;

/**
 * Created by Somebody on 4/22/2017.
 */

public class Presupuesto {

    String name;
    int fname;
    String email;

    public Presupuesto (String name, int fname, String email){
        this.name = name;
        this.fname = fname;
        this.email = email;
    }

    public Presupuesto (){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getFname() {
        return fname;
    }

    public void setFname(Integer fname) {
        this.fname = fname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
