package com.devansh.apiobserver.repo;

import com.devansh.apiobserver.model.Telemetry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelemetryRepository extends JpaRepository<Telemetry, Long> {
}
