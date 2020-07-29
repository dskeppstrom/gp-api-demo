package com.davidskeppstrom.service;

import com.davidskeppstrom.exception.ResourceException;
import com.davidskeppstrom.model.DeviceInfoRequestResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface DeviceService {

    @PostMapping("/create")
    DeviceInfoRequestResponse createDevice(final DeviceInfoRequestResponse deviceCreateRequest) throws ResourceException;

    @PutMapping("/update")
    DeviceInfoRequestResponse updateDevice(final DeviceInfoRequestResponse deviceUpdateRequest) throws ResourceException;

    @PostMapping("/find")
    DeviceInfoRequestResponse findDeviceInfo(final DeviceInfoRequestResponse findDeviceInfo) throws ResourceException;
}
