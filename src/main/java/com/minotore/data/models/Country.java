package com.minotore.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Country {
    private String name;
    private String isoCode;
    private String ContinentName;
    private Boolean isInEuropeanUnion;
}
