package com.davidskeppstrom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceInfoRequestResponse {

    private String serial;

    private String machineCode;

    private String deviceName;
}
