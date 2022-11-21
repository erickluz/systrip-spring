package com.erick.passenger.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Utils {
	
    public static String toJson(Object object) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(object);
        } catch (JsonProcessingException e) {
        	throw new RuntimeException("Falha ao converter objeto. " + object.toString());
        }
    }

	public static Object fromJson(String json, Class<?> classe) {
		ObjectMapper om = new ObjectMapper();
		try {
			return om.readValue(json, classe);
		} catch (Exception e) {
			throw new RuntimeException("Falha ao converter objeto. " + json);
		}
	}
}
