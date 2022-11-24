package com.erick.testrunner.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class DriverAvailability implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idDriver;
    private String currentAddress;
    private String district;
}
