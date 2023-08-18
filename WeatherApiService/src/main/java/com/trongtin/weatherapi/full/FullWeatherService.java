package com.trongtin.weatherapi.full;

import com.trongtin.weatherapi.common.Location;
import com.trongtin.weatherapi.location.LocationNotFoundException;
import com.trongtin.weatherapi.location.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class FullWeatherService {

    private LocationRepository repo;

    public FullWeatherService(LocationRepository repo) {
        super();
        this.repo = repo;
    }

    public Location getByLocation(Location locationFromIP) {
        String cityName = locationFromIP.getCityName();
        String countryCode = locationFromIP.getCountryCode();

        Location locationInDB = repo.findByCountryCodeAndCityName(countryCode, cityName);

        if (locationInDB == null) {
            throw new LocationNotFoundException(countryCode, cityName);
        }

        return locationInDB;
    }
}

