package com.ashan.demo.service.impl;

import com.ashan.demo.dto.CountriesDTO;
import com.ashan.demo.dto.CountriesPropertyDTO;
import com.ashan.demo.dto.CountryDTO;
import com.ashan.demo.service.CountryService;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);
    @Value("${external.api.uri}")
    private String uri;

    @Override
    public CountriesDTO all() {
        CountriesDTO countriesDTO = new CountriesDTO();

        try {
            RestTemplate restTemplate = new RestTemplate();
            JSONObject results = restTemplate.getForObject(uri + "/countries/iso", JSONObject.class);
            List<CountriesPropertyDTO> countries = new ArrayList<>();

            if (results != null) {
                if (!(boolean) results.get("error")) {
                    ArrayList countryList = (ArrayList) results.get("data");
                    for (Object country : countryList) {
                        CountriesPropertyDTO countriesPropertyDTO = new CountriesPropertyDTO();
                        LinkedHashMap<String, String> dataMap = (LinkedHashMap<String, String>) country;
                        countriesPropertyDTO.setName(dataMap.get("name"));
                        countriesPropertyDTO.setCountry_code(dataMap.get("Iso2"));
                        countries.add(countriesPropertyDTO);
                    }
                    countriesDTO.setCountries(countries);
                }
            }
        } catch (Exception e) {
            logger.error("Error occurred - " + e);
        }
        return countriesDTO;
    }

    @Override
    public CountryDTO getCountry(String country) {
        System.out.println();
        CountryDTO countryDTO = new CountryDTO();

        try {
            countryDTO.setCountry_code(this.getCountryCodeAndCapital(country).get("iso2"));
            countryDTO.setCapital(this.getCountryCodeAndCapital(country).get("capital"));
            countryDTO.setFlag_file_url(this.getCountryFlag(country));
            countryDTO.setPopulation(this.getPopulation(country));
            countryDTO.setName(country.toUpperCase());
        } catch (Exception e) {
            logger.error("Error occurred getCountry - " + e);
        }
        return countryDTO;
    }

    private LinkedHashMap<String, String> getCountryCodeAndCapital(String requestCountry) {
        LinkedHashMap<String, String> returnData = new LinkedHashMap<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            JSONObject results = restTemplate.getForObject(uri + "/countries/capital", JSONObject.class);

            if (results != null) {
                if (!(boolean) results.get("error")) {
                    ArrayList countryList = (ArrayList) results.get("data");
                    for (Object country : countryList) {
                        LinkedHashMap<String, String> dataMap = (LinkedHashMap<String, String>) country;
                        if (dataMap.get("name").toLowerCase().equals(requestCountry)) {
                            returnData.put("iso2", dataMap.get("iso2"));
                            returnData.put("capital", dataMap.get("capital"));
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error occurred getCountryCodeAndCapital - " + e);
        }
        return returnData;
    }

    private String getCountryFlag(String requestCountry) {
        LinkedHashMap<String, String> returnData = new LinkedHashMap<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            JSONObject results = restTemplate.getForObject(uri + "/countries/flag/images", JSONObject.class);

            if (results != null) {
                if (!(boolean) results.get("error")) {
                    ArrayList countryList = (ArrayList) results.get("data");
                    for (Object country : countryList) {
                        LinkedHashMap<String, String> dataMap = (LinkedHashMap<String, String>) country;
                        if (dataMap.get("name").toLowerCase().equals(requestCountry)) {
                            return dataMap.get("flag");
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error occurred getCountryCodeAndCapital - " + e);
        }
        return "";
    }

    private Integer getPopulation(String country) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            JSONObject response = restTemplate.getForObject(uri + "/countries/population", JSONObject.class);
            ArrayList dataPop = (ArrayList) response.get("data");
            for (Object resCountry : dataPop) {
                LinkedHashMap<String, Object> resCountryMap = (LinkedHashMap<String, Object>) resCountry;
                if (resCountryMap.get("country").toString().equalsIgnoreCase(country)) {
                    ArrayList populationCounts = (ArrayList) resCountryMap.get("populationCounts");
                    LinkedHashMap<String, Integer> popCount = (LinkedHashMap<String, Integer>) populationCounts.get(populationCounts.size() - 1);
                    return popCount.get("value");
                }
            }
        } catch (Exception e) {
            logger.error("Error occurred in getPopulation - " + e);
        }

        return 0;
    }

}
