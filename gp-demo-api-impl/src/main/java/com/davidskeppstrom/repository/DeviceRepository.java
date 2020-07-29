package com.davidskeppstrom.repository;

import java.util.Optional;

import com.davidskeppstrom.model.DeviceEntity;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends CrudRepository<DeviceEntity, String> {
    Optional<DeviceEntity> findByMachineCode(final String machineName);
}
