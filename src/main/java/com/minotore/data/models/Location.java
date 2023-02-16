package com.minotore.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Location {
    private Double latitude;
    private Double longitude;

    private String timeZone;
    private Integer populationDensity;
    private Integer averageIncome;
    private Integer metroCode;
}
