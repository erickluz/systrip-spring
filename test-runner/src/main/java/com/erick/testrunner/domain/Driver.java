package com.erick.testrunner.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Driver {
	private Integer id;
	private String name;
	private String cpf;
	private String phoneNumber;
	private String userName;
	private String password;
	private String email;
}
