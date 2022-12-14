package com.erick.testrunner.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {
	private Integer id;
	private String name;
	private String cpf;
	private String phoneNumber;
	private String userName;
	private String password;
	private String email;

}
