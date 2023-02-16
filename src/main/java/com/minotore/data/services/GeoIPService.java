package com.minotore.data.services;

import com.minotore.data.models.Country;
import com.minotore.data.models.GeoIP;
import com.minotore.data.models.Location;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;

@Service
public class GeoIPService {

    @Autowired
    private DatabaseReader databaseReader;

    public GeoIP getGeoIP(String ipAddress) throws IOException, GeoIp2Exception {
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        CityResponse response = databaseReader.city(inetAddress);
        Location location= new Location(response.getLocation().getLatitude(),response.getLocation().getLongitude(),
                response.getLocation().getTimeZone(),response.getLocation().getPopulationDensity(),
                response.getLocation().getAverageIncome(),response.getLocation().getMetroCode());

        Country country= new Country(response.getCountry().getName(),response.getCountry().getIsoCode(),
                response.getContinent().getName(),response.getCountry().isInEuropeanUnion());


        //  response.getMostSpecificSubdivision();
        return new GeoIP(ipAddress,response.getCity().getName(),location, country);

    }


    public GeoIP getGeoIP(HttpServletRequest request) throws IOException, GeoIp2Exception {
        String ipAddress= getIpAddress(request);
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        CityResponse response = databaseReader.city(inetAddress);
        Location location= new Location(response.getLocation().getLatitude(),response.getLocation().getLongitude(),
                response.getLocation().getTimeZone(),response.getLocation().getPopulationDensity(),
                response.getLocation().getAverageIncome(),response.getLocation().getMetroCode());

    Country country= new Country(response.getCountry().getName(),response.getCountry().getIsoCode(),
            response.getContinent().getName(),response.getCountry().isInEuropeanUnion());


      //  response.getMostSpecificSubdivision();
        return new GeoIP(ipAddress,response.getCity().getName(),location, country);
    }



    public String getIpAddress(HttpServletRequest request) {
        String xff = request.getHeader("X-Forwarded-For");
        if (xff != null) {
            return xff;//.split(",")[0];
        }
        return request.getRemoteAddr();
    }


}
