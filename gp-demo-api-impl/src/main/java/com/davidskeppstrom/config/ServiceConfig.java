package com.davidskeppstrom.config;

import com.davidskeppstrom.model.DeviceEntity;
import com.davidskeppstrom.model.DeviceInfoRequestResponse;
import com.davidskeppstrom.repository.DeviceRepository;
import com.davidskeppstrom.service.DeviceService;
import com.davidskeppstrom.service.DeviceServiceImpl;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public DeviceService deviceService(final DeviceRepository deviceRepository) {
        final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(DeviceInfoRequestResponse.class, DeviceEntity.class);
        return new DeviceServiceImpl(deviceRepository,mapperFactory.getMapperFacade());
    }
}
