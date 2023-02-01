package com.ashan.demo.dto;

import java.util.List;
import java.util.Map;

public class CountriesDTO {
//    private Map<String, CountriesPropertyDTO> countries;
    private List<CountriesPropertyDTO> countries;

    public List<CountriesPropertyDTO> getCountries() {
        return countries;
    }

    public void setCountries(List<CountriesPropertyDTO> countries) {
        this.countries = countries;
    }
}
