package com.ashan.demo.dto;

public class CountryPostDataDTO {

    private String country;

    public CountryPostDataDTO(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
