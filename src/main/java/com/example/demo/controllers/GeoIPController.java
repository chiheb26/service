package com.example.demo.controllers;

import com.example.demo.models.GeoIP;
import com.example.demo.services.GeoIPService;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/geoip")
public class GeoIPController {

    @Autowired
    private GeoIPService geoIPService;

    @GetMapping("/{ipAddress}")
    public ResponseEntity<GeoIP> getGeoIP(@PathVariable String ipAddress) throws IOException, GeoIp2Exception {
        GeoIP geoIP = geoIPService.getGeoIP(ipAddress);
        return ResponseEntity.ok(geoIP);
    }



    @GetMapping
    public ResponseEntity<GeoIP> getGeoIP(HttpServletRequest request) throws IOException, GeoIp2Exception {

        GeoIP geoIP = geoIPService.getGeoIP(request);
        return ResponseEntity.ok(geoIP);
    }

    /*
    @GetMapping("/ip")
    public String getIpAddress(HttpServletRequest request) {
        return request.getRemoteAddr();
    }
    */


    @GetMapping("/ip")
    public String getIpAddress(HttpServletRequest request) {
        String xff = request.getHeader("X-Forwarded-For");
        if (xff != null) {
            return xff;//.split(",")[0];
        }
        return request.getRemoteAddr();
    }


}
