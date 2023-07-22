package com.trongtin.weatherapi.hourly;

import java.util.List;

import com.trongtin.weatherapi.common.HourlyWeather;
import com.trongtin.weatherapi.common.HourlyWeatherId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface HourlyWeatherRepository extends CrudRepository<HourlyWeather, HourlyWeatherId> {

    @Query("""
			SELECT h FROM HourlyWeather h WHERE
			h.id.location.code = ?1 AND h.id.hourOfDay > ?2
			AND h.id.location.trashed = false
			""")
    public List<HourlyWeather> findByLocationCode(String locationCode, int currentHour);
}
