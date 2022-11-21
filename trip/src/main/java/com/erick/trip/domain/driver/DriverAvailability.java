package com.erick.trip.domain.driver;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DriverAvailability implements Serializable {
    private static final long serialVersionUID = 1L;
    
	private Long idDriver;
    private String currentAddress;
    private String district;
}
