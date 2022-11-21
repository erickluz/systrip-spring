package com.erick.driver.domain;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DriverAvailability implements Serializable {
    private static final long serialVersionUID = 1L;
    
	private Integer idDriver;
    private String currentAddress;
    private String district;
}
