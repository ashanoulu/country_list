package com.ashan.demo.service;

import com.ashan.demo.dto.CountriesDTO;
import com.ashan.demo.dto.CountryDTO;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface CountryService {
    CountriesDTO all();
    CountryDTO getCountry(String country) throws IOException;
}
