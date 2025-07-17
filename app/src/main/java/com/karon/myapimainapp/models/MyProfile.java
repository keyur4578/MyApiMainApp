package com.karon.myapimainapp.models;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
 class Address{
    public String address;
    public String city;
    public String state;
    public String stateCode;
    public String postalCode;
    public Coordinates coordinates;
    public String country;
}

 class Bank{
    public String cardExpire;
    public String cardNumber;
    public String cardType;
    public String currency;
    public String iban;
}

 class Company{
    public String department;
    public String name;
    public String title;
    public Address address;
}

 class Coordinates{
    public double lat;
    public double lng;
}

 class Crypto{
    public String coin;
    public String wallet;
    public String network;
}

class Hair{
    public String color;
    public String type;
}

public class MyProfile{
    public int id;
    public String firstName;
    public String lastName;
    public String maidenName;
    public int age;
    public String gender;
    public String email;
    public String phone;
    public String username;
    public String password;
    public String birthDate;
    public String image;
    public String bloodGroup;
    public double height;
    public double weight;
    public String eyeColor;
    public Hair hair;
    public String ip;
    public Address address;
    public String macAddress;
    public String university;
    public Bank bank;
    public Company company;
    public String ein;
    public String ssn;
    public String userAgent;
    public Crypto crypto;
    public String role;
}


