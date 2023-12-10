package com.example.trial2;

public class TransactionItem {
    private String label;
    private String value;

    public TransactionItem(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }
}