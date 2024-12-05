package com.quasar.operation.infrastructure.adapter.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.quasar.operation.aplication.dto.SatelliteSplitRequest;
import com.quasar.operation.aplication.usecase.DetermineMessageAndLocation;
import com.quasar.operation.aplication.dto.Response;
import com.quasar.operation.aplication.dto.SatelliteRequest;
import com.quasar.operation.core.domain.models.Satellite;
import com.quasar.operation.utils.LocalizationHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.quasar.operation.aplication.usecase.DetermineMessageAndLocation.execute;

@RestController
@RequestMapping("/api")
@Validated
public class QuasarController {

    @PostMapping("/topsecret")
    public ResponseEntity<Response> topSecret(@Valid @RequestBody SatelliteRequest satelliteRequest) {

        try {
            DetermineMessageAndLocation.DeterminationResult result = execute(satelliteRequest.getSatellites());

            Response response = new Response(result.getLocation(), result.getMessage());
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response(null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(null, LocalizationHelper.getMessage("error.unexpected")));
        }

    }

    @PostMapping("/topsecret_split/{satellite_name}")
    public ResponseEntity<String> updateSatelliteData(
            @PathVariable String satellite_name,
            @Valid @RequestBody SatelliteSplitRequest request) {

        Satellite satellite = new Satellite(satellite_name, request.getDistance(), request.getMessage());
        if (DetermineMessageAndLocation.storeSatelliteData(satellite, satellite_name)) {
            return ResponseEntity.ok("Data for satellite " + satellite_name + " updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Satellite with name " + satellite_name + " already exists.");
        }
    }

    @GetMapping("/topsecret_split")
    public ResponseEntity<Response> getDetermination() {
        if (DetermineMessageAndLocation.satelliteDataSize() < 3) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response(null, LocalizationHelper.getMessage("error.determine")));
        }

        try {
            List<Satellite> satellites = List.of(
                    DetermineMessageAndLocation.getSatelliteData("kenobi"),
                    DetermineMessageAndLocation.getSatelliteData("skywalker"),
                    DetermineMessageAndLocation.getSatelliteData("sato")
            );

            DetermineMessageAndLocation.DeterminationResult result = DetermineMessageAndLocation.execute(satellites);

            return ResponseEntity.ok(new Response(result.getLocation(), result.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response(null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(null, "Unexpected error occurred."));
        }
    }

}
