package com.devansh.apiobserver.controller;

import com.devansh.apiobserver.dto.TelemetryDTO;
import com.devansh.apiobserver.model.Telemetry;
import com.devansh.apiobserver.repo.TelemetryRepository;
import com.devansh.apiobserver.service.CollectionService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/controller")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class CollectorController {
    private final CollectionService collectionService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/collect")
    public void collect(@RequestBody TelemetryDTO telemetry) {
        telemetry.setTimestamp(Instant.now());
        collectionService.createTelemetry(telemetry);
        String topic = "/topic/" + telemetry.getService();
        simpMessagingTemplate.convertAndSend(topic, telemetry);
    }
}
