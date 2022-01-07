package com.Soheily.LerningSpringboot.model;

import java.util.UUID;

public class User {
    private  UUID userId;

    private final String userFirstName;
    private final String userLastName;
    private final Gender gender;
    private final Integer age;
    private final String email;

    public User(UUID userId, String userFirstName, String userLastName, Gender gender, Integer age, String email) {
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.gender = gender;
        this.age = age;
        this.email = email;
    }

    public UUID getUserId() {
        return userId;
    }
    public void setUserUid(UUID uuid) {this.userId=uuid;}

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public Gender getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }



    public enum Gender {
        Male,
        FeMale
    }
}
