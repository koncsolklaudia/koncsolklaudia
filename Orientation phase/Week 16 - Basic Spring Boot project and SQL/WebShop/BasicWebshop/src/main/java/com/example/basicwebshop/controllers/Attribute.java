package com.example.basicwebshop.controllers;

public enum Attribute {

    ITEMS, ERROR, AVERAGE, SEARCH, NEWITEM, RESULT, ID, ITEM ;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
