package com.quasar.operation.aplication.dto;

import com.quasar.operation.core.domain.models.Satellite;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class SatelliteRequest {

    @NotEmpty
    @Valid
    private List<Satellite> satellites;

    public List<Satellite> getSatellites() {
        return satellites;
    }

    public void setSatellites(List<Satellite> satellites) {
        this.satellites = satellites;
    }
}