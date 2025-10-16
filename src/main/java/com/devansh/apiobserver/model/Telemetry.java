package com.devansh.apiobserver.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Telemetry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String service;
    private String endpoint;
    private String method;
    private int status;
    private long latencyMs;
    private Instant timestamp;

}
