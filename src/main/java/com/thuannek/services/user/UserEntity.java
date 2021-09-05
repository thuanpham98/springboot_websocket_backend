package com.thuannek.services.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "users")
public class UserEntity {


    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_email", nullable = false)
    private String email;

    @Column(name = "user_token", nullable = false)
    private String tokeString;

    public UserEntity(){
        this.email="";
        this.tokeString="";
        this.name="";
        this.userId="";
    }

    public UserEntity(String name, String userId , String email , String tokeString){
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

    public void setUserId(String userId){
        this.userId = userId;
    }

    public void setUserEmail(String email){
        this.email = email;
    }

    public void setUserName(String name){
        this.name= name;
    }

    public void setUserToken(String token){
        this.tokeString = token;
    }

    @Override
    public String toString(){
        return ("User :  email = " + email.toString());
    }

    public UserModel toUserModel(){
        return new UserModel(
            this.name,
            this.userId,
            this.email,
            this.tokeString
        );
    }

    public void fromUserModel(UserModel model){
        this.email = model.getUserEmail();
        this.name = model.getUserName();
        this.tokeString = model.getUserToken();
    }

}
