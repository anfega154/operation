package com.quasar.operation.infrastructure.adapter.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.quasar.operation.aplication.usecase.DetermineMessageAndLocation;
import com.quasar.operation.aplication.dto.Response;
import com.quasar.operation.aplication.dto.SatelliteRequest;
import com.quasar.operation.utils.LocalizationHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import static com.quasar.operation.aplication.usecase.DetermineMessageAndLocation.execute;

@RestController
@RequestMapping("/api")
@Validated
public class QuasarController {
    private final ObjectMapper objectMapper = new ObjectMapper();

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
}
