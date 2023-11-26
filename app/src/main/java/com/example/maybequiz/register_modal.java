package com.example.maybequiz;

public class register_modal {
    String uname,email,pass,gender,country;

    public register_modal() {
    }
    public register_modal(String uname, String email, String pass, String gender, String country) {
        this.uname = uname;
        this.email = email;
        this.pass = pass;
        this.gender = gender;
        this.country = country;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
