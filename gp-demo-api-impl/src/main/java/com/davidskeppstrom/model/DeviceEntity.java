package com.davidskeppstrom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceEntity {

    @Id()
    @Column(name="SERIAL_NO")
    private String serial;

    @Column(name="MACHINE_CODE")
    private String machineCode;

    @Column(name="DEVICE_NAME")
    private String deviceName;
}
