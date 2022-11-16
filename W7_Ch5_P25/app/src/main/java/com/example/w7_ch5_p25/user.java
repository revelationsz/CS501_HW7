package com.example.w7_ch5_p25;

public class user {

    private String firstName;
    private String lastName;
    private String email;

    public user(String email, String firstName, String lastName ){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString(){
        return this.firstName +" " + this.lastName + " " + this.email;
    }
}
