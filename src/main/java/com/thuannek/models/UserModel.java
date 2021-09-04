package com.thuannek.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// import com.fasterxml.jackson.core.sym.Name;
// import org.springframework.stereotype.Service;

// import org.springframework.beans.factory.annotation.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "users")
public class UserModel {


    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_email", nullable = false)
    private String email;

    @Column(name = "user_token", nullable = false)
    private String tokeString;

    public UserModel(){
        this.email="";
        this.tokeString="";
        this.name="";
        this.userId="";

    }

    public UserModel(String name, String userId , String email , String tokeString){
        this.name = name;
        this.userId = userId;
        this.email = email;
        this.tokeString = tokeString;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id ;

    public long getId(){
        return this.id;
    }

    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserEmail(){
        return this.email;
    }

    public void setUserEmail(String email){
        this.email = email;
    }

    
    public String getUserName(){
        return this.name;
    }

    public void setUserName(String name){
        this.name= name;
    }

    public String getUserToken(){
        return this.tokeString;
    }

    public void setUserToken(String token){
        this.tokeString = token;
    }

    @Override
    public Object clone(){
        UserModel model = null;
        try {
            model = (UserModel) super.clone();
        } catch (CloneNotSupportedException e) {
            model = new UserModel(this.name,this.userId,this.email,this.tokeString);
        }
        return model;
    }

    @Override
    public String toString(){
        return ("User email = " + email.toString());
    }

}
