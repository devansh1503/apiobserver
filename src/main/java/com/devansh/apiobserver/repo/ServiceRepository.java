package com.devansh.apiobserver.repo;

import com.devansh.apiobserver.model.APIService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<APIService,Long> {
    APIService findByName(String name);
}
