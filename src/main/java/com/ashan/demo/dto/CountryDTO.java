package com.ashan.demo.dto;

public class CountryDTO {

    private String name;
    private String country_code;
    private String capital;
    private Integer population;
    private String flag_file_url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getFlag_file_url() {
        return flag_file_url;
    }

    public void setFlag_file_url(String flag_file_url) {
        this.flag_file_url = flag_file_url;
    }
}
