package com.devansh.apiobserver.service;

import com.devansh.apiobserver.dto.TelemetryDTO;
import com.devansh.apiobserver.model.Telemetry;
import com.devansh.apiobserver.repo.ServiceRepository;
import com.devansh.apiobserver.repo.TelemetryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollectionService {
    private final TelemetryRepository telemetryRepository;
    private final ServiceRepository serviceRepository;

    public void createTelemetry(TelemetryDTO telemetryDTO) {
        Telemetry telemetry = new Telemetry();
        telemetry.setId(telemetryDTO.getId());
        telemetry.setService(serviceRepository.findByName(telemetryDTO.getService()));
        telemetry.setStatus(telemetryDTO.getStatus());
        telemetry.setMethod(telemetryDTO.getMethod());
        telemetry.setEndpoint(telemetryDTO.getEndpoint());
        telemetry.setLatencyMs(telemetryDTO.getLatencyMs());
        telemetry.setTimestamp(telemetryDTO.getTimestamp());
        telemetryRepository.save(telemetry);
    }
}
