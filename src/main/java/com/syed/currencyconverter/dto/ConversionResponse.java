package com.syed.currencyconverter.dto;

public class ConversionResponse {

    private double amount;
    private String from;
    private String to;
    private double rate;
    private double convertedAmount;

    public ConversionResponse() {
    }

    public ConversionResponse(double amount, String from, String to, double rate, double convertedAmount) {
        this.amount = amount;
        this.from = from;
        this.to = to;
        this.rate = rate;
        this.convertedAmount = convertedAmount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(double convertedAmount) {
        this.convertedAmount = convertedAmount;
    }
}