package com.example.demo.services;

import com.example.demo.models.GeoIP;
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
        return new GeoIP(ipAddress,response.getCity().getName(), response.getCountry().getName());
    }


    public GeoIP getGeoIP(HttpServletRequest request) throws IOException, GeoIp2Exception {
        String ipAddress= getIpAddress(request);
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        CityResponse response = databaseReader.city(inetAddress);
        return new GeoIP(ipAddress,response.getCity().getName(), response.getCountry().getName());
    }



    public String getIpAddress(HttpServletRequest request) {
        String xff = request.getHeader("X-Forwarded-For");
        if (xff != null) {
            return xff;//.split(",")[0];
        }
        return request.getRemoteAddr();
    }


}
