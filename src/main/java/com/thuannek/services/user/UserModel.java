package com.thuannek.services.user;

public final class UserModel {

    private final  String userId;
    private final String name;
    private final String email;
    private final String tokeString;

    public UserModel(String name, String userId , String email , String tokeString){
        this.name = name;
        this.userId = userId;
        this.email = email;
        this.tokeString = tokeString;
    }

    public String getUserId(){
        return this.userId;
    }

    public String getUserEmail(){
        return this.email;
    }
    
    public String getUserName(){
        return this.name;
    }

    public String getUserToken(){
        return this.tokeString;
    }

    // public

    @Override
    public String toString(){
        return ("User model :  email = " + email.toString());
    }
}
