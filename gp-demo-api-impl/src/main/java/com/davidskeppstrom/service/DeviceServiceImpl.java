package com.davidskeppstrom.service;

import java.util.Optional;

import com.davidskeppstrom.exception.ResourceException;
import com.davidskeppstrom.model.DeviceEntity;
import com.davidskeppstrom.model.DeviceInfoRequestResponse;
import com.davidskeppstrom.repository.DeviceRepository;
import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final MapperFacade mapperFacade;

    @Override
    public DeviceInfoRequestResponse createDevice(final DeviceInfoRequestResponse deviceCreateRequest) throws ResourceException {
        validateRequest(deviceCreateRequest);

        final DeviceEntity deviceEntity = mapperFacade.map(deviceCreateRequest, DeviceEntity.class);

        final DeviceEntity savedEntity = deviceRepository.save(deviceEntity);

        return mapperFacade.map(savedEntity, DeviceInfoRequestResponse.class);
    }

    @Override
    public DeviceInfoRequestResponse updateDevice(final DeviceInfoRequestResponse deviceUpdateRequest) throws ResourceException {
        validateRequest(deviceUpdateRequest);

        final DeviceEntity deviceEntity = mapperFacade.map(deviceUpdateRequest, DeviceEntity.class);

        final DeviceEntity savedEntity = deviceRepository.save(deviceEntity);

        return mapperFacade.map(savedEntity, DeviceInfoRequestResponse.class);
    }

    @Override
    public DeviceInfoRequestResponse findDeviceInfo(final DeviceInfoRequestResponse findDeviceInfo) throws ResourceException {
        validateRequest(findDeviceInfo);

        final DeviceEntity deviceEntity = mapperFacade.map(findDeviceInfo, DeviceEntity.class);

        Optional<DeviceEntity> deviceInfo = Optional.empty();
        if (StringUtils.isNotBlank(findDeviceInfo.getSerial())
                && StringUtils.isNotBlank(findDeviceInfo.getMachineCode())) {
            deviceInfo = deviceRepository.findById(deviceEntity.getSerial());

            if (!deviceInfo.isPresent()) {
                deviceInfo = deviceRepository.findByMachineCode(findDeviceInfo.getMachineCode());
            }

            if (!deviceInfo.isPresent()) {
                throw new ResourceException("machine.code.not.found", "ER0002", "The machine code does not match our "
                        + "records");
            }
        } else if (StringUtils.isNotBlank(findDeviceInfo.getSerial())) {
            deviceInfo = deviceRepository.findById(deviceEntity.getSerial());

            if (!deviceInfo.isPresent()) {
                throw new ResourceException("serial.number.not.found", "ER004", "The serial number does not match our"
                        + " records");
            }
        } else if (StringUtils.isNotBlank(findDeviceInfo.getMachineCode())) {
            deviceInfo = deviceRepository.findByMachineCode(deviceEntity.getMachineCode());

            if (!deviceInfo.isPresent()) {
                throw new ResourceException("machine.code.not.found", "ER0002", "The machine code does not match our "
                        + "records");
            }
        }

        DeviceInfoRequestResponse response = null;
        if (deviceInfo.isPresent()) {
            response = mapperFacade.map(deviceInfo.get(), DeviceInfoRequestResponse.class);
        }
        return response;
    }

    private void validateRequest(final DeviceInfoRequestResponse deviceRequest) throws ResourceException {
        if (!deviceRequest.getSerial().matches("")) {
            throw new ResourceException("serial.number.invalid", "ER003", "The serial number entered can include a-z, "
                    + "A-Z, 0-9 and hyphen.  Please correct your entry.");
        }

        if (StringUtils.isEmpty(deviceRequest.getMachineCode())) {
            throw new ResourceException("machine.code.invalid", "ER001", "The machine code is incorrect.  Check the "
                    + "Machine code you provided and try again.");
        }
    }
}
