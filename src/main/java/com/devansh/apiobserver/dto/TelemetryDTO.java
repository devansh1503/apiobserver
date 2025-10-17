package com.devansh.apiobserver.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class TelemetryDTO {
    Long id;
    String service;
    String endpoint;
    String method;
    int status;
    long latencyMs;
    Instant timestamp;
}
