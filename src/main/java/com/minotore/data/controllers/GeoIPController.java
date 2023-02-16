package com.minotore.data.controllers;

import com.minotore.data.models.GeoIP;
import com.minotore.data.services.GeoIPService;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/geoip")
@CrossOrigin("*")
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
