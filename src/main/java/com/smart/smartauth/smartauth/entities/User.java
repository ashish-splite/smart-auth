package com.smart.smartauth.smartauth.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;
    private String username;
    private String mobileNo;
    private String profilePic;

    public User(String name, String email, String username, String mobileNo, String profilePic) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.mobileNo = mobileNo;
        this.profilePic = profilePic;
    }

}
