package com.thuannek.commons;

public enum JwtUtilKey {
    SECRET_KEY("thuanthuan");

    private String value;

    private JwtUtilKey(String value){
        this.value = value;
    }

    public String getSecretKey(){
        return value;
    }
}
