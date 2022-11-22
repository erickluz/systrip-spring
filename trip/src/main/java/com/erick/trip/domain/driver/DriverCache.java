package com.erick.trip.domain.driver;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@RedisHash("DriverCache")
public class DriverCache {
	private Integer id;
	@Indexed
	private String address;
	@Indexed
	private String district;
	private Long ordering;

}
