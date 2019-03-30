package com.tripworld.hotel.config;

import com.tripworld.hotel.helpers.ModelConverter;
import com.tripworld.hotel.repository.HotelRepository;
import com.tripworld.hotel.service.HotelService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackageClasses = {HotelService.class, ModelConverter.class})
@EntityScan(basePackages = "com.tripworld.hotel.domain")
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = HotelRepository.class)
public class JpaEnversConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;

    }
}
