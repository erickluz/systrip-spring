package com.erick.trip.domain.passenger;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TripRequestPassenger {
    private Integer idPassenger;
    private String addressOrigin;
    private String addressDestiny;
    private String district;
}
