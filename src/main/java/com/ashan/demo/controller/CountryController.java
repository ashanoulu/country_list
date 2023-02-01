package com.ashan.demo.controller;


import com.ashan.demo.dto.CountriesDTO;
import com.ashan.demo.dto.CountryDTO;
import com.ashan.demo.service.CountryService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@OpenAPIDefinition(info=@Info(title="Fetch Country List"))
public class CountryController {

    private final CountryService countryService;
    Logger logger = LoggerFactory.getLogger(CountryController.class);

    CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    /* Get all countries */
    @Operation(summary = "Get all countries with code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - Incorrect URL"),
            @ApiResponse(responseCode = "500", description = "Something went wrong in the serverside, please check again")
    })
    @GetMapping("/countries")
    ResponseEntity<CountriesDTO> all() throws Exception {

        logger.info("Begin countries all method");
        logger.info("Fetch countries");
        CountriesDTO countriesList = countryService.all();

        return new ResponseEntity<>(countriesList, HttpStatus.OK);
    }


    /* Get country details */
    @Operation(summary = "Get country details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - Incorrect URL"),
            @ApiResponse(responseCode = "500", description = "Something went wrong in the serverside, please check again")
    })
    @GetMapping("/countries/{name}")
    ResponseEntity<CountryDTO> getCountry(@PathVariable("name") String name) throws Exception {

        logger.info("Begin countries all method");
        logger.info("Fetch countries");
        CountryDTO country = countryService.getCountry(name);

        return new ResponseEntity<>(country, HttpStatus.OK);
    }

}
