package com.example.realtimedb_test;

public class Studentebi {

    String name, surname, ID, ProfilePicture, email;


    public Studentebi() {

    }

    public Studentebi(String name, String surname, String ID, String profilePicture, String email) {
        this.name = name;
        this.surname = surname;
        this.ID = ID;
        this.ProfilePicture = profilePicture;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getProfilePicture() {
        return ProfilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.ProfilePicture = profilePicture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
