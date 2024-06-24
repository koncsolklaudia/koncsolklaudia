package com.example.bankofsimba.models;

public class BankAccount {

    private String name;
    private String animalType;
    private Integer balance;

    Boolean isKing;

    Boolean isGood;
    public Boolean getKing() {
        return isKing;
    }

    public void setKing(Boolean king) {
        isKing = king;
    }

    public BankAccount(String name, String animalType, Integer balance, Boolean isKing, Boolean isGood) {
        this.name = name;
        this.animalType = animalType;
        this.balance = balance;
        this.isKing = isKing;
        this.isGood = isGood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Boolean getGood() {
        return isGood;
    }

    public void setGood(Boolean good) {
        isGood = good;
    }

}
