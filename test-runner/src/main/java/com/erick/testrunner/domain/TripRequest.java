package com.erick.testrunner.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TripRequest {
    private Integer idPassenger;
    private String addressOrigin;
    private String addressDestiny;
    private String district;
}
