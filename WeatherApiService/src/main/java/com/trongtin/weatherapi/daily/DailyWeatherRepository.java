package com.trongtin.weatherapi.daily;

import java.util.List;

import com.trongtin.weatherapi.common.DailyWeather;
import com.trongtin.weatherapi.common.DailyWeatherId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface DailyWeatherRepository extends CrudRepository<DailyWeather, DailyWeatherId> {

    @Query("""
			SELECT d FROM DailyWeather d WHERE d.id.location.code = ?1
			AND d.id.location.trashed = false
			""")
    public List<DailyWeather> findByLocationCode(String locationCode);
}

