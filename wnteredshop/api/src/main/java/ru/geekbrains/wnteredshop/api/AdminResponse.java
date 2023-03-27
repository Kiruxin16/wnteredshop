package ru.geekbrains.wnteredshop.api;

public class AdminResponse {
    private String value;

    public AdminResponse(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
