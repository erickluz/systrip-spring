package com.erick.trip.domain.passenger;

import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@RedisHash("PassengerCache")
public class PassengerCache {
	private Integer id;
	private String addressOrigin;
	private String addressdestiny;
	private String district;
}
