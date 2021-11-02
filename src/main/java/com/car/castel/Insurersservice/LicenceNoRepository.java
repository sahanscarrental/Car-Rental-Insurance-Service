package com.car.castel.Insurersservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LicenceNoRepository extends JpaRepository<LicenceNo, UUID> {
}
