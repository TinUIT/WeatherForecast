package com.trongtin.weatherapi.full;

import com.trongtin.weatherapi.CommonUtility;
import com.trongtin.weatherapi.GeolocationService;
import com.trongtin.weatherapi.common.Location;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/full")
public class FullWeatherApiController {

    private GeolocationService locationService;
    private FullWeatherService weatherService;
    private ModelMapper modelMapper;

    public FullWeatherApiController(GeolocationService locationService, FullWeatherService weatherService,
                                    ModelMapper modelMapper) {
        super();
        this.locationService = locationService;
        this.weatherService = weatherService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<?> getFullWeatherByIPAddress(HttpServletRequest request) {
        String ipAddress = CommonUtility.getIPAddress(request);

        Location locationFromIP = locationService.getLocation(ipAddress);
        Location locationInDB = weatherService.getByLocation(locationFromIP);

        return ResponseEntity.ok(entity2DTO(locationInDB));
    }

    private FullWeatherDTO entity2DTO(Location entity) {
        FullWeatherDTO dto = modelMapper.map(entity, FullWeatherDTO.class);

        // do not show the field location in realtime_weather object
        dto.getRealtimeWeather().setLocation(null);
        return dto;
    }
}

